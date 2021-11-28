package controllers.toppage;

import java.io.IOException;
import java.time.LocalDate;

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
        // ログインユーザーのIDを取得
        int userId = u.getId();
        String day = request.getParameter("day");

        int weight = Integer.parseInt(u.getWeight());
        int userWeight = weight;

        /**
         *
         */
        day = "loginDay";
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



        int calorieBurned = 0;
        try {
            CalorieConsumption calorieconsumption = (CalorieConsumption) em.createNamedQuery("getUseridAndDayByCalorieConsumption", CalorieConsumption.class)
                    .setParameter("userid", userId)
                    .setParameter("day", day)
                    .getSingleResult();
            calorieBurned = calorieconsumption.getCaloriesburned();
        }catch(NoResultException e) {

        }
        request.setAttribute("calorieconsumption", calorieBurned);

        // リクエストパラメータを取得する
        String StepCount = request.getParameter("StepCount");
        //Stringからintへの変換
        int Stepcount = Integer.parseInt(StepCount);

        //歩数を元に消費カロリー計算
        int Stride = 70;
        int METs = 4;
        int num1 = 100000;
        int stepcount =  Stepcount;
        double num2 = 1.05;
        double AverageSpeed = 4.5;

        double km = stepcount * Stride / num1;
        double Time = km / AverageSpeed;
        double CaloriesBurned = userWeight * METs * Time * num2;
        request.setAttribute("CaloriesBurned", CaloriesBurned);


        // 現在日時情報で初期化されたインスタンスの取得
        LocalDate nowDate = LocalDate.now();
        request.setAttribute("loginDay", nowDate);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/toppage/index.jsp");
        rd.forward(request, response);
    }

}
