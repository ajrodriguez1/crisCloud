package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.model.Publication;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/CreatePublicationServlet")
public class CreatePublicationServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("true".equals(req.getSession().getAttribute("userAdmin"))) {
			PublicationDAO pdao = new PublicationDAO();
			ResearcherDAO rdao = new ResearcherDAO();
			Publication p = new Publication();
			Researcher r = rdao.read(req.getParameter("first_author"));
			List<String> r_publications = r.getPublications(); 
			
			p.setId(req.getParameter("id"));
			p.setTitle(req.getParameter("title"));
			p.setAuthors(Arrays.asList(req.getParameter("authors").split(";")));
			p.setEid(req.getParameter("eid"));
			p.setPublicationName(req.getParameter("publication_name"));
			p.setPublicationDate(req.getParameter("publication_date"));
			p.setFirstAuthor(req.getParameter("first_author"));
			
			// Create publication, add publication to its author and redirect
			pdao.create(p);
			r_publications.add(p.getId());
			r.setPublications(r_publications);
			rdao.update(r);
			resp.sendRedirect(req.getContextPath() + "/PublicationServlet?id=" + p.getId());

		} else {
			req.setAttribute("message", "You are not allowed to view this page");
			getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);
		}
	}
}
