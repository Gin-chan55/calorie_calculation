package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CalorieIntake;
import utils.DBUtil;

/**
 * Servlet implementation class CalorieIntakeServlet
 */
@WebServlet("/CalorieIntakeServlet")
public class CalorieIntakeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalorieIntakeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        int totalcalorieintake = 0;
        try {
            CalorieIntake calorieintake = (CalorieIntake) em.createNamedQuery("getTotalCalorieIntakeByCalorieIntake", CalorieIntake.class)
                    .setParameter("totalcalorieintake", totalcalorieintake)
                    .getSingleResult();
            totalcalorieintake = calorieintake.getTotalcalorieintake();
        }catch(NoResultException e) {
        }
        request.setAttribute("totalcalorieintake", totalcalorieintake);












    }

}
