package controllers.toppage;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        String userid = request.getParameter("userid");
        String day = request.getParameter("day");

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        CalorieIntake calorieintake = (CalorieIntake) em.createNamedQuery("getUseridAndDayByCalorieIntake", CalorieIntake.class)
                .setParameter("userid", userid)
                .setParameter("day", day)
                .getSingleResult();

        request.setAttribute("calorieintake", calorieintake);

        // セッションスコープに保存されたログインユーザ情報を取得
        User u = (User) request.getSession().getAttribute("login_user");
        // ログインユーザーのIDを取得
        int userId = u.getId();


        // 現在日時情報で初期化されたインスタンスの取得
        LocalDate nowDate = LocalDate.now();
        request.setAttribute("loginDay", nowDate);




        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/toppage/index.jsp");
        rd.forward(request, response);
    }

}
