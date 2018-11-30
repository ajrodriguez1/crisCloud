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

@WebServlet("/UpdateResearcherCredentialsServlet")
public class UpdateResearcherCredentialsServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResearcherDAO rdao = new ResearcherDAO();
		
		Researcher researcher = rdao.read(req.getParameter("uid"));
		researcher.setEmail(req.getParameter("email"));
		researcher.setPassword(req.getParameter("password"));
		
		// Update information requested
		rdao.update(researcher);
		
		resp.sendRedirect(req.getContextPath() + "/ResearcherServlet" + "?id=" + researcher.getId());
	}
}
