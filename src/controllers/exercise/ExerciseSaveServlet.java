package controllers.exercise;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalorieConsumption;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ExerciseSaveServlet
 */
@WebServlet("/exercise/save")
public class ExerciseSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseSaveServlet() {
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
        String caloriesburned = request.getParameter("caloriesburned");
        String Stepcount = request.getParameter("StepCount");
        double CaloriesBurned = 0;
        double StepCount = 0;
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            try {
                CaloriesBurned = Integer.parseInt(caloriesburned);
                StepCount = Integer.parseInt(Stepcount);
            }catch(NumberFormatException e) {

            }
            em.getTransaction().begin();
            CalorieConsumption calorieconsumption = new CalorieConsumption();

            try {
                calorieconsumption = em.createNamedQuery("getUseridAndDayByCalorieConsumption", CalorieConsumption.class)
                        .setParameter("userid", userId)
                        .setParameter("day", day)
                        .getSingleResult();
            }catch(NoResultException e) {

            }
            calorieconsumption.setUserid(userId);
            calorieconsumption.setDay(day);
            calorieconsumption.setCaloriesburned(CaloriesBurned);
            calorieconsumption.setCaloriesburned(StepCount);
            em.persist(calorieconsumption);


            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "消費カロリーを保存しました。");
            response.sendRedirect(request.getContextPath() + "/meal/index?day="+day);
        } else {
            request.getSession().setAttribute("flush", "消費カロリーの保存に失敗しました。");
            response.sendRedirect(request.getContextPath() + "/meal/index?day="+day);
        }
    }


}
