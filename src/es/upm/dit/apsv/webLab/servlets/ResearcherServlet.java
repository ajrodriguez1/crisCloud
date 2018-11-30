package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/ResearcherServlet")
public class ResearcherServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResearcherDAO rdao = new ResearcherDAO();
		PublicationDAO pdao = new PublicationDAO();
		Researcher researcher = rdao.read(req.getParameter("id"));
		
		// Researcher information requested
		req.getSession().setAttribute("researcher", researcher);
		
		// Look for all his publications
		req.getSession().setAttribute("publications", pdao.parsePublications(researcher.getPublications()));
		
		getServletContext().getRequestDispatcher("/ResearcherView.jsp").forward(req, resp);
	}
}
