package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

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

        // Userのインスタンスを生成
        User m = new User();
        List<String> errors = new ArrayList<String>();
        // フォームに初期値を設定、さらにエラーメッセージを送る
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("user", m);
        request.setAttribute("errors", errors);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/new.jsp");
        rd.forward(request, response);

    }
}
