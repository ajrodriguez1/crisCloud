package es.upm.dit.apsv.webLab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import es.upm.dit.apsv.webLab.model.Publication;
import es.upm.dit.apsv.webLab.model.Researcher;
import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;

@WebServlet("/GenerateCVServlet")
public class GenerateCVServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream sout = resp.getOutputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PublicationDAO pdao = new PublicationDAO();
		ResearcherDAO rdao = new ResearcherDAO();
		Researcher researcher = rdao.read(req.getParameter("id"));
		
		//Create pdf object
		PdfDocument pdf = new PdfDocument(new PdfWriter(baos));
		Document document = new Document(pdf);
		
		//Add paragraphs
		Paragraph p = new Paragraph("Curriculum Vitae").setFontSize(20);
		document.add(p);

		p = new Paragraph("Full name: " + researcher.getName()+ " " + researcher.getLastName());
		document.add(p);
		
		p = new Paragraph("Email: "+ researcher.getEmail());
		document.add(p);

		//Add a table with publications
		//The table should be initialized with an array of floats indicating the relative width of each column
		Table table = new Table(new float[]{7, 1});
		table.addHeaderCell("Publication title");
		table.addHeaderCell("Citations");
		for (Publication pub : pdao.parsePublications(researcher.getPublications())){
		    table.addCell(pub.getTitle());
		    table.addCell(Integer.toString(pub.getCiteCount()));
		}
		document.add(table);

		//Close the document
		document.close();
		pdf.close();

		//Write the file
		resp.setContentType("application/pdf");
		resp.setContentLength(baos.size());
		baos.writeTo(sout);
	}
}
