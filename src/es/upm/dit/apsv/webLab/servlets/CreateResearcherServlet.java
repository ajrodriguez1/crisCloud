package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/CreateResearcherServlet")
public class CreateResearcherServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("true".equals(req.getSession().getAttribute("userAdmin"))) {
			ResearcherDAO rdao = new ResearcherDAO();
			Researcher r = new Researcher();
			
			r.setId(req.getParameter("uid"));
			r.setName(req.getParameter("name"));
			r.setLastName(req.getParameter("last_name"));
			
			// Create researcher and redirect
			rdao.create(r);
			resp.sendRedirect(req.getContextPath() + "/ResearcherServlet?id=" + r.getId());

		} else {
			req.setAttribute("message", "You are not allowed to view this page");
			getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);
		}
	}
}
