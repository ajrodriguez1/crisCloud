package es.upm.dit.apsv.webLab.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.model.Publication;
import es.upm.dit.apsv.webLab.model.Researcher;

@WebServlet("/PopulatePublicationsServlet")
@MultipartConfig
public class PopulatePublicationsServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part filePart = req.getPart("file");
		InputStream fileContent = filePart.getInputStream();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(fileContent, "UTF-8"));
		Publication p = new Publication();
		ResearcherDAO rdao = new ResearcherDAO();
		PublicationDAO pdao = new PublicationDAO();
		
		String line = bReader.readLine();
		while (null != (line = bReader.readLine())) {
		    String[] lSplit = line.split(",");
		    
		    // Get data
		    p = setUpPublication(lSplit);
		    
		    // Look at if the first author of the current publication are in the database,
		    // in that case, insert it
		    if (rdao.read(p.getFirstAuthor()) != null) {
		    	p = updateResearcherPublications(p);
		    	
		    	// Insert publication
		    	pdao.create(p);
		    }
		}
		
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
	}
	
	// 
	private Publication setUpPublication (String[] lSplit) {
		Publication p = new Publication();
				
		p.setId(lSplit[0]);
	    p.setTitle(lSplit[1]);
	    p.setEid(lSplit[2]);
	    p.setPublicationName(lSplit[3]);
	    p.setPublicationDate(lSplit[4]);
	    p.setFirstAuthor(lSplit[5]);
	    // Add authors list without duplicated ids
	    p.setAuthors(cleanDuplicated(Arrays.asList(lSplit[6].split(";"))));

	    return p;
	}
	
	private Publication updateResearcherPublications (Publication p) {
		Researcher r = new Researcher();
		ResearcherDAO rdao = new ResearcherDAO();
		List<String> authors = new ArrayList<String>(p.getAuthors());
		ListIterator<String> itAuthors = authors.listIterator();
		List<String> publications = new ArrayList<String>();
		String researcherId = new String();
		
		while (itAuthors.hasNext()) {
			researcherId = itAuthors.next();

    		// Get each researcher of the list 
			r = rdao.read(researcherId);
			
			// Add publication to that author publications list
			if (r != null) {
				publications = r.getPublications();
				// Add new publication
				publications.add(p.getId());
				
				// Verify that it is not generated any duplicated value
				publications = cleanDuplicated(publications);
				
				// Update researcher
				r.setPublications(publications);
				rdao.update(r);
			} 
			else {
				// If the author does not exit, remove it from the list
				itAuthors.remove();
			}
		}
		
		// Update publication authors list
		p.setAuthors(cleanDuplicated(authors));
				
		return p;
	}
	
	private List<String> cleanDuplicated (List<String> li){
		ListIterator<String> it1, it2;
		String str1, str2 = new String();
		
		it1 = li.listIterator();
		
		while (it1.hasNext()) {
			// String to compare
			str1 = it1.next();
			// Starting since the beginning
			it2 = li.listIterator();
			
			while (it2.hasNext()) {
				str2 = it2.next();
				
				// Compare values
				if (str1 == str2 && it1.nextIndex() != it2.nextIndex()) {
				// Clean value
					it2.remove();
					
					// Restart iterator 2 since the beginning
					it1 = li.listIterator(it1.previousIndex()+1);
				}
			}
		}
		
		return li;
	}
}
