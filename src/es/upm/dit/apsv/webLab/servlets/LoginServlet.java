package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet({"/LoginServlet","/"})
public class LoginServlet extends HttpServlet {

	final String ADMIN = "root";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResearcherDAO rdao = new ResearcherDAO();
		UserService userService = UserServiceFactory.getUserService();
		Researcher researcher = new Researcher();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
//		Researcher researcher = rdao.readAsUser(email, password);
		
		// Administrator mode if user and password are the same
		if (ADMIN.equals(email) && ADMIN.equals(password)) {
			Researcher root = new Researcher();
			
			root.setId("root");
			req.getSession().setAttribute("userAdmin", "true");
			req.getSession().setAttribute("user", root);
			resp.sendRedirect(req.getContextPath() + "/AdminServlet");
			
		// Normal researcher access	
		} else if (null != req.getUserPrincipal()) {
			researcher = rdao.readAsUser(email, password);
			
			req.getSession().setAttribute("userAdmin", "false");
			req.getSession().setAttribute("user", researcher);
			resp.sendRedirect(req.getContextPath() + "/ResearcherServlet" + "?id=" + researcher.getId());
			
		// This user is not register	
		} else {
			req.setAttribute("message", "Invalid user or password");
			getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);
		}
	}

}
