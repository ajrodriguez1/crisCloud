package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.model.Publication;

@WebServlet("/PublicationServlet")
public class PublicationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PublicationDAO pdao = new PublicationDAO();
		ResearcherDAO rdao = new ResearcherDAO();
		String publicationId = req.getParameter("id");
		Publication publication = pdao.read(publicationId);
		
		req.getSession().setAttribute("publication", publication);
		req.getSession().setAttribute("authors", rdao.parseResearchers(publication.getAuthors()));

		getServletContext().getRequestDispatcher("/PublicationView.jsp").forward(req, resp);
	}
}
