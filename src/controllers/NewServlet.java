package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.UserValidator;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        // Userのインスタンスを生成
        User m = new User();

        // mの各フィールドにデータを代入
        String address = "taro";
        m.setAddress(address);

        String password = "hello";
        m.setPassword(password);

        String height = "hello";
        m.setHeight(height);

        String weight = "hello";
        m.setWeight(weight);


        // バリデーションを実行してエラーがあったら新規登録のフォームに戻る
        List<String> errors = UserValidator.validate(m, em);
        if(errors.size() > 0) {
             em.close();

        // フォームに初期値を設定、さらにエラーメッセージを送る
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("user", m);
        request.setAttribute("errors", errors);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/new.jsp");
        rd.forward(request, response);
        } else {
        // データベースに保存
        em.persist(m);
        em.getTransaction().commit();
        request.getSession().setAttribute("flush", "登録が完了しました。");
        em.close();

        // indexのページにリダイレクト
        response.sendRedirect(request.getContextPath() + "/index");
        }
     }
}
