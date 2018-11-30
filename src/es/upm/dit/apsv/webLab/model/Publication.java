package es.upm.dit.apsv.webLab.model;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity (name = "Publication")
public class Publication {
	// Attributes
	@Id
	private String id;
	
	private String title, publicationName, publicationDate, eid, firstAuthor;
	private int citeCount;
	
	private List<String> authors;
	
	public Publication () {
		super();
		// Initialize only collection variables
		this.authors = new ArrayList<String>();
	}
	
	// ******************************************************
	// 						Getters Methods
	// ******************************************************
	public String getId () {
		return this.id;
	}
	
	public String getTitle () {
		return this.title;
	}
	
	public String getPublicationName () {
		return this.publicationName;
	}
	
	public String getPublicationDate () {
		return this.publicationDate;
	}
	
	public String getEid () {
		return this.eid;
	}
	
	public int getCiteCount () {
		return this.citeCount;
	}
	
	public String getFirstAuthor () {
		return this.firstAuthor;
	}
	
	public List<String> getAuthors () {
		return this.authors;
	}
	
	// ******************************************************
	// 						Setters Methods
	// ******************************************************
	public void setId (String id) {
		this.id = id;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public void setPublicationName (String publicationName) {
		this.publicationName = publicationName;
	}
	
	public void setPublicationDate (String publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	public void setAuthors (List<String> authors) {
		this.authors = authors;
	}
	
	public void setEid (String eid) {
		this.eid = eid;
	}
	
	public void setCiteCount (int citeCount) {
		this.citeCount = citeCount;
	}
	
	public void setFirstAuthor (String firstAuthor) {
		this.firstAuthor = firstAuthor;
	}
}