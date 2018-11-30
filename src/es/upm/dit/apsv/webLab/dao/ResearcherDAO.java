package es.upm.dit.apsv.webLab.dao;

import java.util.List;

import com.googlecode.objectify.cmd.LoadType;

import java.util.ArrayList;
import java.util.Collection;

import static com.googlecode.objectify.ObjectifyService.ofy;

import es.upm.dit.apsv.webLab.model.Publication;
import es.upm.dit.apsv.webLab.model.Researcher;

public class ResearcherDAO{
	
	/**
	 * Insert a new researcher
	 * 
	 * @param researcher Data of the researcher to insert
	 */
	public void create (Researcher researcher) {
		ofy().save().entity(researcher).now();
	}
	
	/**
	 * Look for a researcher by his Id
	 * 
	 * @param researcherId 
	 * @return Researcher information
	 */
	public Researcher read (String researcherId) {
		return ofy().load().type(Researcher.class).id(researcherId).now();
	}
	
	/**
	 * Update the information of a researcher
	 * 
	 * @param researcher Researcher information to update
	 */
	public void update (Researcher researcher) {
		ofy().save().entity(researcher).now();
	}
	
	/**
	 * Delete a researcher
	 * 
	 * @param researcher to be deleted
	 */
	public void delete (Researcher researcher) {
		ofy().delete().entity(researcher).now();
	}
	
	/**
	 * Get all the database information
	 * 
	 * @return All researchers
	 */
	public List<Researcher> readAll (){
		return ofy().load().type(Researcher.class).list();
	}
	
	/**
	 * Get researcher information looking for email and password
	 * 
	 * @param email Researcher email
	 * @param password Researcher password
	 * @return Researcher if this was found, else null
	 */
	public Researcher readAsUser (String email, String password) {
		return ofy().load().type(Researcher.class).filter("email", email).filter("password", password) .first().now();
	}
	
	/**
	 * Look for all the researchers whose ids have been provided
	 * 
	 * @param ids List of researchers ids
	 * @return List with all the researchers ids requested
	 */
	public List<Researcher> parseResearchers (Collection<String> ids) {
		List<Researcher> final_list = new ArrayList<Researcher>(ofy().load().type(Researcher.class).ids(ids).values());
		
		return final_list;
	}
}