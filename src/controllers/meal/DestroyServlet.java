package controllers.meal;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Addition;
import models.CalorieIntake;
import models.Meal;
import models.User;
import utils.DBUtil;


/**
 * 食品削除処理
 */
@WebServlet("/meal/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        // セッションスコープに保存されたログインユーザ情報を取得
        User u = (User) request.getSession().getAttribute("login_user");
        // ログインユーザーのIDを取得
        int userId = u.getId();
        String day = request.getParameter("day");

        String[] deleteMeals = request.getParameterValues("delete-meal");
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            em.getTransaction().begin();

            for (var i = 0; i < deleteMeals.length; ++i) {

                    Meal m = em.find(Meal.class, Integer.parseInt(deleteMeals[i]));

                    // テーブルを削除
                    em.remove(m);

            }

            List<Meal> meals = em.createNamedQuery("getUseridAndDayByMeals", Meal.class)
                    .setParameter("userid", userId)
                    .setParameter("day", day)
                    .getResultList();

            int totalcalorie = 0;
            for (Meal meal : meals) {
                int quantity = meal.getQuantity();
                Addition addition= meal.getAddition();
                int calorie = addition.getCaloriesper();
                int mealcalorie = quantity * calorie;
                totalcalorie += mealcalorie;
            }
            CalorieIntake calorieIntake = new CalorieIntake();
            try {
                calorieIntake = em.createNamedQuery("getUseridAndDayByCalorieIntake", CalorieIntake.class)
                        .setParameter("userid", userId)
                        .setParameter("day", day)
                        .getSingleResult();
            }catch(NoResultException e) {

            }
            calorieIntake.setUserid(userId);
            calorieIntake.setDay(day);
            calorieIntake.setTotalcalorieintake(totalcalorie);
            em.persist(calorieIntake);

            em.getTransaction().commit();
            response.sendRedirect(request.getContextPath() + "/meal/edit?day="+day);
            request.getSession().setAttribute("flush", "食品を削除しました。");
        } else {
            request.getSession().setAttribute("flush", "食品の削除に失敗しました。");
            response.sendRedirect(request.getContextPath() + "/meal/edit?day="+day);
        }
   }

}
