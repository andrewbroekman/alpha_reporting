package com.codinginfinity.research.report;

import com.codinginfinity.research.people.Person;
import com.codinginfinity.research.people.ResearchGroup;
import com.codinginfinity.research.publication.LifeCycleState;
import com.codinginfinity.research.publication.Publication;
import com.codinginfinity.research.publication.PublicationType;

import javax.persistence.*;
import java.text.*;
import java.util.*;

public class Populate {
	public static void main (String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();

		entitymanager.getTransaction().begin();

		//Create com.codinginfinity.research.people.Person objects

		//Person1
		Person person1 = new Person();

		person1.setFirstNames("Paul");
		person1.setSurname("TestGuy1");

		ResearchGroup testGroup1 = new ResearchGroup();
		testGroup1.setName("Test Research Group 1");

		entitymanager.persist(testGroup1);

		person1.setGroup(testGroup1);
		person1.setResearchGroupAssociationType("groupLeader");

		SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
		String dateSInput = "2015-01-01";
		String dateEInput = "2017-01-01";
		Date dS = new Date();
		Date dE = new Date();

		try {
			dS = tmp.parse(dateSInput);
			dE = tmp.parse(dateEInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}

		person1.setStartDate(dS);
		person1.setEndDate(dE);
		person1.setPublications(null);
		person1.setPrimaryEmail("paul@email.com");
		person1.setOrganisation("University of Pretoria: Computer Science Department");

		//Person2

		Person person2 = new Person();

		person2.setFirstNames("Tom");
		person2.setSurname("TestGuy2");
		person2.setGroup(testGroup1);
		person2.setResearchGroupAssociationType("member");

		dateSInput = "2016-01-01";
		dateEInput = "2017-01-01";

		try {
			dS = tmp.parse(dateSInput);
			dE = tmp.parse(dateEInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}

		person2.setStartDate(dS);
		person2.setEndDate(dE);
		person2.setPublications(null);
		person2.setPrimaryEmail("tom@email.com");
		person2.setOrganisation("University of Pretoria: Computer Science Department");

		//Person3

		Person person3 = new Person();

		person3.setFirstNames("Sally");
		person3.setSurname("TestGuy3");

		ResearchGroup testGroup2 = new ResearchGroup();
		testGroup2.setName("Test Research Group 2");

		entitymanager.persist(testGroup2);

		person3.setGroup(testGroup2);
		person3.setResearchGroupAssociationType("groupLeader");

		dateSInput = "2015-06-06";
		dateEInput = "2016-06-06";

		try {
			dS = tmp.parse(dateSInput);
			dE = tmp.parse(dateEInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}

		person3.setStartDate(dS);
		person3.setEndDate(dE);
		person3.setPublications(null);
		person3.setPrimaryEmail("sally@email.com");
		person3.setOrganisation("University of Pretoria: Computer Science Department");
		
		//Person4
		Person person4 = new Person();

		person4.setFirstNames("Sammy");
		person4.setSurname("TestGuy4");

		person4.setGroup(testGroup2);
		person4.setResearchGroupAssociationType("member");

		dateSInput = "2016-06-06";
		dateEInput = "2016-08-06";

		try {
			dS = tmp.parse(dateSInput);
			dE = tmp.parse(dateEInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}

		person4.setStartDate(dS);
		person4.setEndDate(dE);
		person4.setPublications(null);
		person4.setPrimaryEmail("sammy@email.com");
		person4.setOrganisation("University of Pretoria: Computer Science Department");

		//Person5
		Person person5 = new Person();

		person5.setFirstNames("Jackson");
		person5.setSurname("TestGuy5");

		person5.setGroup(testGroup2);
		person5.setResearchGroupAssociationType("member");

		dateSInput = "2016-01-06";
		dateEInput = "2016-08-06";

		try {
			dS = tmp.parse(dateSInput);
			dE = tmp.parse(dateEInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}

		person5.setStartDate(dS);
		person5.setEndDate(dE);
		person5.setPublications(null);
		person5.setPrimaryEmail("jackson@email.com");
		person5.setOrganisation("University of Pretoria: Computer Science Department");

		//Person6
		Person person6 = new Person();

		person6.setFirstNames("Bob");
		person6.setSurname("TestGuy6");

		ResearchGroup testGroup3 = new ResearchGroup();
		testGroup3.setName("Test Research Group 3");

		entitymanager.persist(testGroup3);

		person6.setGroup(testGroup3);
		person6.setResearchGroupAssociationType("groupLeader");

		dateSInput = "2015-06-06";
		dateEInput = "2016-06-06";

		try {
			dS = tmp.parse(dateSInput);
			dE = tmp.parse(dateEInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}

		person6.setStartDate(dS);
		person6.setEndDate(dE);
		person6.setPublications(null);
		person6.setPrimaryEmail("bob@email.com");
		person6.setOrganisation("University of Pretoria: Computer Science Department");

		entitymanager.persist(person1);
		entitymanager.persist(person2);
		entitymanager.persist(person3);
		entitymanager.persist(person4);
		entitymanager.persist(person5);
		entitymanager.persist(person6);

		//Creating Lists of Authors
		List<Person> authors1 = new ArrayList<Person>();
		List<Person> authors2 = new ArrayList<Person>();
		List<Person> authors3 = new ArrayList<Person>();
		List<Person> authors4 = new ArrayList<Person>();

		authors1.add(person1);
		authors1.add(person2);
		authors2.add(person3);
		authors2.add(person4);
		authors2.add(person5);
		authors3.add(person6);
		authors4.add(person3);
		authors4.add(person5);

		// Creating Publications

		// Publication1

		Publication publication1 = new Publication();

		publication1.setTitle("Test Publication 1");
		publication1.setAuthors(authors1);
		publication1.setGroup(testGroup1);

		LifeCycleState state1 = new LifeCycleState();
		state1.setLifeCycleState("InProgress");
		state1.setMisc("59");

		publication1.setLifeCycleState(state1);

		PublicationType pubType1 = new PublicationType();
		pubType1.setName("High-Impact Journal");
		pubType1.setAccreditationPoints(1.0);

		publication1.setPublicationType(pubType1);

		String pubDate = "2016-04-01";
		Date pD = new Date();

		try {
			pD = tmp.parse(dateSInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}
		publication1.setEnvisagedPublicationDate(pD);

		// Publication2

		Publication publication2 = new Publication();

		publication2.setTitle("Test Publication 2");
		publication2.setAuthors(authors2);
		publication2.setGroup(testGroup2);

		LifeCycleState state2 = new LifeCycleState();
		state2.setLifeCycleState("Published");
		state2.setMisc("2016-02-01");

		publication2.setLifeCycleState(state2);

		PublicationType pubType2 = new PublicationType();
		pubType2.setName("Accredited Book");
		pubType2.setAccreditationPoints(3.0);

		publication2.setPublicationType(pubType2);

		pubDate = "2016-02-15";
		pD = new Date();

		try {
			pD = tmp.parse(pubDate);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}
		publication2.setEnvisagedPublicationDate(pD);
		
		// Publication3

		Publication publication3 = new Publication();

		publication3.setTitle("Test Publication 3");
		publication3.setAuthors(authors3);
		publication3.setGroup(testGroup3);

		LifeCycleState state3 = new LifeCycleState();
		state3.setLifeCycleState("InProgress");
		state3.setMisc("78");

		publication3.setLifeCycleState(state3);

		PublicationType pubType3 = new PublicationType();
		pubType3.setName("High-Impact Journal");
		pubType3.setAccreditationPoints(1.5);

		publication3.setPublicationType(pubType3);

		pubDate = "2016-04-01";
		pD = new Date();

		try {
			pD = tmp.parse(dateSInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}
		publication3.setEnvisagedPublicationDate(pD);

		// Publication4

		Publication publication4 = new Publication();

		publication4.setTitle("Test Publication 4");
		publication4.setAuthors(authors4);
		publication4.setGroup(testGroup2);

		LifeCycleState state4 = new LifeCycleState();
		state4.setLifeCycleState("Published");
		state4.setMisc("2016-03-15");

		publication4.setLifeCycleState(state4);

		PublicationType pubType4 = new PublicationType();
		pubType4.setName("Accredited Book");
		pubType4.setAccreditationPoints(2.8);

		publication4.setPublicationType(pubType4);

		pubDate = "2016-04-01";
		pD = new Date();

		try {
			pD = tmp.parse(dateSInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}
		publication4.setEnvisagedPublicationDate(pD);

		// Publication5

		Publication publication5 = new Publication();

		publication5.setTitle("Test Publication 5");
		publication5.setAuthors(authors1);
		publication5.setGroup(testGroup1);

		LifeCycleState state5 = new LifeCycleState();
		state5.setLifeCycleState("InProgress");
		state5.setMisc("85");

		publication5.setLifeCycleState(state5);

		PublicationType pubType5 = new PublicationType();
		pubType5.setName("High-Impact Journal");
		pubType5.setAccreditationPoints(1.5);

		publication5.setPublicationType(pubType5);

		pubDate = "2016-05-01";
		pD = new Date();

		try {
			pD = tmp.parse(dateSInput);
		} catch (ParseException e) {
			System.out.println("Unparseable.");
		}
		publication5.setEnvisagedPublicationDate(pD);

		entitymanager.persist(publication1);
		entitymanager.persist(publication2);
		entitymanager.persist(publication3);
		entitymanager.persist(publication4);
		entitymanager.persist(publication5);

		entitymanager.getTransaction().commit();

//		Scanner in = new Scanner(System.in);
//		System.out.println("Enter to close...");
//		String s = in.nextLine();

		entitymanager.close();
		emfactory.close();
	}
}
