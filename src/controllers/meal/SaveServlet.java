package controllers.meal;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalorieIntake;
import models.User;
import utils.DBUtil;


/**
 * 総カロリー保存処理
 */
@WebServlet("/meal/save")
public class SaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveServlet() {
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
        String totalcalorieStr = request.getParameter("totalCalorie");
        int totalcalorie = 0;
        String _token = request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            try {
                totalcalorie = Integer.parseInt(totalcalorieStr);
            }catch(NumberFormatException e) {

            }
            em.getTransaction().begin();
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
            request.getSession().setAttribute("flush", "総カロリーを保存しました。");
            response.sendRedirect(request.getContextPath() + "/meal/index?day="+day);
        } else {
            request.getSession().setAttribute("flush", "総カロリーの保存に失敗しました。");
            response.sendRedirect(request.getContextPath() + "/meal/index?day="+day);
        }
    }


}
