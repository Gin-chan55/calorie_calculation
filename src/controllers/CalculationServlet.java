package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

        // セッションスコープに保存されたログインユーザ情報を取得
        User u = (User) request.getSession().getAttribute("login_user");
        // ログインユーザーのIDを取得
        int userId = u.getId();
        String day = request.getParameter("day");
        int weight = Integer.parseInt(u.getWeight());
        int userWeight = weight;

     // 現在日時情報で初期化されたインスタンスの取得
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate nowDate = LocalDate.now();
        String LoginDate = nowDate.format(formatter);
        request.setAttribute("loginDay", LoginDate);

        // リクエストパラメータを取得する。
        String StepCount = request.getParameter("StepCount");
        String calorieintake = request.getParameter("totalcalorieintake");


        //Stringからintへの変換
        int Stepcount = Integer.parseInt(StepCount);

        // JSPに値を送る
        request.setAttribute("calorieintake", calorieintake);
        request.setAttribute("StepCount", Stepcount);


        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        try {
            CalorieConsumption stepcount = (CalorieConsumption) em.createNamedQuery("getStepcountByCalorieConsumption", CalorieConsumption.class)
                    .setParameter("stepcount", Stepcount)
                    .getSingleResult();
            Stepcount = stepcount.getStepcount();
        }catch(NoResultException e) {
        }
        request.setAttribute("StepCount", Stepcount);


        double CaloriesBurned = 0;
        //歩数を元に消費カロリー計算
        int Stride = 70; //歩幅70㎝
        int METs = 4;
        int num1 = 100000;
        double num2 = 1.05;
        double AverageSpeed = 4.5; //㎞/時

        double km = Stepcount * Stride / num1;
        double Time = km / AverageSpeed;
        CaloriesBurned = userWeight * METs * Time * num2;

        //小数点第二位以下を切り捨て
        BigDecimal cd = new BigDecimal(CaloriesBurned);
        BigDecimal caloriesburned = cd.setScale(1, RoundingMode.HALF_UP);

        request.setAttribute("CaloriesBurned", caloriesburned);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/toppage/index.jsp");
        rd.forward(request, response);






    }



}




