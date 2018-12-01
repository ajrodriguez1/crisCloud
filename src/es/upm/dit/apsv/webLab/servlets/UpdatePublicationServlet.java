package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.model.Publication;
import es.upm.dit.apsv.webLab.dao.PublicationDAO;

@WebServlet("/UpdatePublicationServlet")
public class UpdatePublicationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PublicationDAO pdao = new PublicationDAO();
		Publication publication = pdao.read(req.getParameter("id"));
	
		// Data to modify
		publication.setPublicationName(req.getParameter("name"));
		publication.setTitle(req.getParameter("title"));
		publication.setPublicationDate(req.getParameter("publication_date"));
		publication.setCiteCount(Integer.parseInt(req.getParameter("cite_count")));
		publication.setFirstAuthor(req.getParameter("first_author"));
		publication.setAuthors(Arrays.asList(req.getParameter("authors").split(";")));
		
		// Update information requested
		pdao.update(publication);
		
		resp.sendRedirect(req.getContextPath() + "/PublicationServlet" + "?id=" + publication.getId());
	}
}
