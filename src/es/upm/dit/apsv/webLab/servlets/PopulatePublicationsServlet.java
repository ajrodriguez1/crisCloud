package es.upm.dit.apsv.webLab.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.model.Publication;

@WebServlet("/PopulatePublicationsServlet")
@MultipartConfig
public class PopulatePublicationsServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filePart = req.getPart("file");
		InputStream fileContent = filePart.getInputStream();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(fileContent));
		Publication p = new Publication();
		PublicationDAO pdao = new PublicationDAO();
		
		String line = bReader.readLine();
		while (null != (line = bReader.readLine())) {
		    String[] lSplit = line.split(",");
		    
		    // Get data
		    p.setId(lSplit[0]);
		    p.setTitle(lSplit[1]);
		    p.setEid(lSplit[2]);
		    p.setPublicationName(lSplit[3]);
		    p.setPublicationDate(lSplit[4]);
		    p.setFirstAuthor(lSplit[5]);
		    p.setAuthors(Arrays.asList(lSplit[6].split(";")));
		    
		    pdao.create(p);
		}
		
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
	}
}
