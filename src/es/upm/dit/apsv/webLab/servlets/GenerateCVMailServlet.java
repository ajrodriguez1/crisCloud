package es.upm.dit.apsv.webLab.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.codec.Base64.InputStream;
import com.itextpdf.io.source.ByteArrayOutputStream;

import es.upm.dit.apsv.webLab.model.Researcher;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;

@WebServlet("/GenerateCVMailServlet")
public class GenerateCVMailServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ResearcherDAO rdao = new ResearcherDAO();
		Researcher researcher = rdao.read(req.getParameter("id"));
		
		//Initialize the mail session
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		
		//Create the message
		Message msg = new MimeMessage(session);
		//Emails can be sent from any address from the application domain @[gae_project_id].appspotmail.com
		try {
			msg.setFrom(new InternetAddress("root@gae_project_id.appspotmail.com", "CRIS System"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(researcher.getEmail(), researcher.getName()));
			msg.setSubject("Message Subject");
			msg.setText("Message body");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Attach the pdf
		Multipart mp = new MimeMultipart();
		MimeBodyPart attachment = new MimeBodyPart();
		//baos is the stream in which the pdf is written
		ByteArrayInputStream attachmentDataStream = new ByteArrayInputStream(baos.toByteArray());
		try {
			attachment.setFileName("cv.pdf");
			attachment.setContent(attachmentDataStream, "application/pdf");
			mp.addBodyPart(attachment);
			msg.setContent(mp);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Send the email
		try {
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
