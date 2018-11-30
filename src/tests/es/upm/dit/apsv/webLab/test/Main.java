package tests.es.upm.dit.apsv.webLab.test;

import es.upm.dit.apsv.webLab.dao.PublicationDAO;
import es.upm.dit.apsv.webLab.dao.ResearcherDAO;
import es.upm.dit.apsv.webLab.model.*;

import java.lang.System;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Publication publication = createPublication();
		Researcher researcher = createResearcher();

		daoResearcher(researcher);
		daoPublication(publication);
	}
	
	static void daoPublication(Publication publication) {
		Publication p = new Publication();
		List<Publication> ps = new ArrayList<Publication>();
		
		PublicationDAO pdao = new PublicationDAO();
		List<String> ids = new ArrayList<String>();

		ids.add(publication.getId());
		ids.add(publication.getId());
		ids.add(publication.getId());
		ids.add(publication.getId());

		pdao.create(publication);
		p = pdao.read(publication.getId());
		pdao.update(publication);
		pdao.delete(publication);
		ps = pdao.readAll();
		ps = pdao.parsePublications(ids);

		System.out.println("PublicationDAO OK!");
	}

	static void daoResearcher(Researcher researcher) {
		Researcher r = new Researcher();
		List<Researcher> rs = new ArrayList<Researcher>();
		
		ResearcherDAO rdao = new ResearcherDAO();
		List<String> ids = new ArrayList<String>();

		ids.add(researcher.getId());
		ids.add(researcher.getId());
		ids.add(researcher.getId());
		ids.add(researcher.getId());

		rdao.create(researcher);
		r = rdao.read(researcher.getId());
		rdao.update(researcher);
		rdao.delete(researcher);
		rs = rdao.readAll();
		r = rdao.readAsUser(researcher.getEmail(), researcher.getPassword());
		rs = rdao.parseResearchers(ids);

		System.out.println("ResearcherDAO OK!");
	}

	static Publication createPublication() {
		Publication publication = new Publication();
		List<String> authors = new ArrayList<String>();

		authors.add("1");
		authors.add("2");
		authors.add("4");
		authors.add("5");
		authors.add("6");

		// Publication class test kit
		publication.setId("id45");
		publication.setTitle("Title");
		publication.setFirstAuthor("Antonio");
		publication.setPublicationDate("15/05/2018");
		publication.setPublicationName("The title");
		publication.setEid("4568sss");
		publication.setCiteCount(45);
		publication.setAuthors(authors);

		System.out.println("Publication OK!");

		return publication;
	}

	static Researcher createResearcher() {
		Researcher researcher = new Researcher();
		List<String> publications = new ArrayList<String>();

		publications.add("1");
		publications.add("2");
		publications.add("3");
		publications.add("4");
		publications.add("5");

		// Researcher class test kit
		researcher.setId("id1");
		researcher.setName("Antonio Javier");
		researcher.setLastName("Rodriguez");
		researcher.setEmail("anto@hotmail.com");
		researcher.setPassword("hello4444");
		researcher.setEid("45666");
		researcher.setScopusUrl("http://google.com");
		researcher.setPublications(publications);

		System.out.println("Researcher OK!");

		return researcher;
	}
}
