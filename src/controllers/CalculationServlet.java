package controllers;

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
 * Servlet implementation class CalculationServlet
 */
@WebServlet("/CalculationServlet")
public class CalculationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

        // セッションスコープに保存されたログインユーザ情報を取得
        User u = (User) request.getSession().getAttribute("login_user");
        // ログインユーザーのIDを取得
        int userId = u.getId();
        String day = request.getParameter("day");
        int weight = Integer.parseInt(u.getWeight());
        int userWeight = weight;

        // リクエストパラメータを取得する。（歩数）
        String StepCount = request.getParameter("StepCount");
        //Stringからintへの変換
        int Stepcount = Integer.parseInt(StepCount);
        // リクエストパラメータを取得する。（消費カロリー）
        String caloriesBurned= request.getParameter("CaloriesBurned");
        //Stringからintへの変換
        int Caloriesburned = Integer.parseInt(caloriesBurned);

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        try {
            CalorieConsumption calorieconsumption = (CalorieConsumption) em.createNamedQuery("getUseridAndDayAndStepcountByCalorieConsumption", CalorieConsumption.class)
                    .setParameter("userid", userId)
                    .setParameter("day", day)
                    .setParameter("stepCount", Stepcount)
                    .getSingleResult();
            Caloriesburned = calorieconsumption.getCaloriesburned();
        }catch(NoResultException e) {
        }
        request.setAttribute("calorieconsumption", Caloriesburned);

        //歩数を元に消費カロリー計算
        int Stride = 70;
        int METs = 4;
        int num1 = 100000;
        double num2 = 1.05;
        double AverageSpeed = 4.5;

        double km = Stepcount * Stride / num1;
        double Time = km / AverageSpeed;
        double CaloriesBurned = userWeight * METs * Time * num2;

        request.setAttribute("CaloriesBurned", CaloriesBurned);



    }



}




