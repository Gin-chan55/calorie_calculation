package controllers.toppage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalorieConsumption;
import models.CalorieIntake;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        // セッションスコープに保存されたログインユーザ情報を取得
        User u = (User) request.getSession().getAttribute("login_user");
        // リクエストパラメータを取得
        int userId = u.getId();
        String day = request.getParameter("day");

        // 現在日時情報で初期化されたインスタンスの取得
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate nowDate = LocalDate.now();
        String LoginDate = nowDate.format(formatter);
        request.setAttribute("loginDay", LoginDate);

        day = LoginDate; //ログインした日付
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        int totalCalorie = 0;
        try {
            CalorieIntake calorieintake = (CalorieIntake) em.createNamedQuery("getUseridAndDayByCalorieIntake", CalorieIntake.class)
                    .setParameter("userid", userId)
                    .setParameter("day", day)
                    .getSingleResult();
            totalCalorie = calorieintake.getTotalcalorieintake();
        }catch(NoResultException e) {

        }
        request.setAttribute("calorieintake", totalCalorie);

        Double caloriesburned = (double) 0;
        try {
            CalorieConsumption calorieconsumption = (CalorieConsumption) em.createNamedQuery("getUseridAndDayByCalorieConsumption", CalorieConsumption.class)
                    .setParameter("userid", userId)
                    .setParameter("day", day)
                    .getSingleResult();
            caloriesburned = calorieconsumption.getCaloriesburned();
        }catch(NoResultException e) {

        }
        request.setAttribute("CaloriesBurned", caloriesburned);



        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/toppage/index.jsp");
        rd.forward(request, response);
    }

}
