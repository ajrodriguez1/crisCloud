package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/_ah/mail/*")
public class CreatePublicationFromEmailServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = null;
		
		try {
			message = new MimeMessage(session, req.getInputStream());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		// Sender
		try {
			String from = new InternetAddress( message.getFrom()[0].toString()).getAddress();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		// Subject
		try {
			String subject = message.getSubject();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
