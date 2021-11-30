package controllers.meal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Addition;
import models.Meal;
import models.User;
import utils.DBUtil;


/**
 * 食事品目編集画面
 */
@WebServlet("/meal/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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

        List<Meal> meals = new ArrayList<Meal>();


        meals = em.createNamedQuery("getUseridAndDayByMeals", Meal.class)
                .setParameter("userid", userId)
                .setParameter("day", day)
                .getResultList();

        request.setAttribute("meals", meals);

        List<Addition> searchResult = new ArrayList<Addition>();
        request.setAttribute("searchResult", searchResult);
        request.setAttribute("day", day);

        request.setAttribute("_token", request.getSession().getId());

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/meal/edit.jsp");
        rd.forward(request, response);
    }


}
