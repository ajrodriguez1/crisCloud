package es.upm.dit.apsv.webLab.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import static com.googlecode.objectify.ObjectifyService.ofy;

import es.upm.dit.apsv.webLab.model.Publication;

public class PublicationDAO {
	
	/**
	 * Insert a new publication
	 * 
	 * @param publication Data of the publication to insert
	 */
	public void create (Publication publication) {
		ofy().save().entity(publication).now();
	}
	
	/**
	 * Look for a publication by his Id
	 * 
	 * @param publicationId 
	 * @return Publication information
	 */
	public Publication read (String publicationId) {
		return ofy().load().type(Publication.class).id(publicationId).now();
	}
	
	/**
	 * Update the information of a publication
	 * 
	 * @param publication Publication information to update
	 */
	public void update (Publication publication) {
		ofy().save().entity(publication).now();
	}
	
	/**
	 * Delete a publication
	 * 
	 * @param publication to be deleted
	 */
	public void delete (Publication publication) {
		ofy().delete().entity(publication).now();
	}

	/**
	 * Get all the database information
	 * 
	 * @return All publications
	 */
	public List<Publication> readAll (){
		return ofy().load().type(Publication.class).list();
	}
	
	/**
	 * Look for all the publications whose ids have been provided
	 * 
	 * @param ids List of publications ids
	 * @return List with all the publications ids requested
	 */
	public List<Publication> parsePublications (Collection<String> ids){
		List<Publication> final_list = new ArrayList<Publication>(ofy().load().type(Publication.class).ids(ids).values());
		
		return final_list;
	}
}