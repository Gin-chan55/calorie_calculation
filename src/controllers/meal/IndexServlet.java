package controllers.meal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalorieIntake;
import models.Meal;
import models.User;
import utils.DBUtil;


/**
 * 食事品目一覧
 */
@WebServlet("/meal/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // セッションスコープに保存されたログインユーザ情報を取得
        User u = (User) request.getSession().getAttribute("login_user");
        // ログインユーザーのIDを取得
        int userId = u.getId();
        String day = request.getParameter("day");


        int totalCalorie = 0;
        List<Meal> meals = new ArrayList<Meal>();
        try {
            CalorieIntake calorieintake = (CalorieIntake) em.createNamedQuery("getUseridAndDayByCalorieIntake", CalorieIntake.class)
                    .setParameter("userid", userId)
                    .setParameter("day", day)
                    .getSingleResult();
            totalCalorie = calorieintake.getTotalcalorieintake();

            meals = em.createNamedQuery("getUseridAndDayByMeals", Meal.class)
                    .setParameter("userid", userId)
                    .setParameter("day", day)
                    .getResultList();

        }catch(NoResultException e) {

        }
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("totalCalorie", totalCalorie);
        request.setAttribute("meals", meals);
        request.setAttribute("day", day);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meal/index.jsp");
        rd.forward(request, response);
    }


}
