package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.ResearcherDAO;

@WebServlet("/ResearcherListServlet")
public class ResearcherListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResearcherDAO rdao = new ResearcherDAO();

		// Search all researchers
		req.getSession().setAttribute("researcher_list", rdao.readAll());
		
		getServletContext().getRequestDispatcher("/ResearchersListView.jsp").forward(req, resp);
	}
}
