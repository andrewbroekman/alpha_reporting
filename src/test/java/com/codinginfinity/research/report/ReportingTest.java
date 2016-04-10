/**
*	@file ReportingTest.java
*       @class ReportingTest
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha
*	@brief A unit test 
*	@section Description
* 	A unit test as a test for the services-oriented mocking framework.
*
*/
package com.codinginfinity.research.report;

import com.codinginfinity.research.report.defaultImpl.GetAccreditationUnitReportRequest;
import com.codinginfinity.research.report.defaultImpl.GetAccreditationUnitReportResponse;
import com.codinginfinity.research.report.defaultImpl.GetProgressReportRequest;
import com.codinginfinity.research.report.defaultImpl.GetProgressReportResponse;
import com.codinginfinity.research.report.defaultImpl.ReportingImpl;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.*;
import java.text.*;
import java.util.Date;
import com.codinginfinity.research.people.Person;
import com.codinginfinity.research.people.ResearchGroup;
import com.codinginfinity.research.publication.LifeCycleState;
import com.codinginfinity.research.publication.Publication;
import com.codinginfinity.research.publication.PublicationType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ReportingTestConfiguration.class})
public class ReportingTest {
    
    /**
     * 
     * @throws Exception 
     */
    @Test(expected = InvalidRequestException.class)
    public void progressReportTest1() throws Exception{
        ReportingMock.getProgressReport(ErrorProgressReportRequestMock);
    }
    
    /**
     * 
     * @throws Exception 
     */
    @Test
    public void progressReportTest2() throws Exception{
        progressReportRes = ReportingMock.getProgressReport(PersonEntityRequestMock);
        assertTrue("SVG was printed",progressReportRes.getSVG());
        assertTrue("PDF was printed",progressReportRes.getPDF());
        assertTrue("HTML was printed", progressReportRes.getHTML());
               
    }
    
    /*@Test
    public void progressReportTest1() throws Exception {
               
        
        
        res = ReportingMock.getProgressReport(ErrorRequestMock);
        assertTrue("SVG was printed",res.getSVG());
        assertTrue("PDF was printed",res.getPDF());
        assertTrue("HTML was printed", res.getHTML());
        
        
       
        
    }*/
    
    @Inject
    private ReportingImpl ReportingMock;
    //===========================================================================================
    @Inject
    private GetProgressReportRequest ErrorProgressReportRequestMock;
    @Inject
    private GetProgressReportRequest PersonEntityRequestMock;
    /*
    TO-DO Define the other Mock objects for each test
    */
    //==========================================================================================
    @Inject
    private GetAccreditationUnitReportRequest ErrorAccUnitReportRequestMock;
    /*
    TO-DO Define the other Mock objects for each test
    */
    
    private GetProgressReportResponse progressReportRes;
    private GetAccreditationUnitReportResponse AccUnitReporRes;
    
    @Before
    public void populateDatabase() 
    {
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ReportingTestUnit");
      EntityManager entitymanager = emfactory.createEntityManager();

      Entitymanager.getTransaction().begin();

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

      try 
      {
        dS = tmp.parse(dateSInput);
        dE = tmp.parse(dateEInput);
      } 
      catch (ParseException e) 
      {
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

      try 
      {
        dS = tmp.parse(dateSInput);
        dE = tmp.parse(dateEInput);
      } 
      catch (ParseException e) 
      {
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

      try 
      {
        dS = tmp.parse(dateSInput);
        dE = tmp.parse(dateEInput);
      } 
      catch (ParseException e) 
      {
        System.out.println("Unparseable.");
      }

      person3.setStartDate(dS);
      person3.setEndDate(dE);
      person3.setPublications(null);
      person3.setPrimaryEmail("sally@email.com");
      person3.setOrganisation("University of Pretoria: Computer Science Department");

      entitymanager.persist(person1);
      entitymanager.persist(person2);
      entitymanager.persist(person3);

      //Creating Lists of Authors
      List<Person> authors1 = new ArrayList<Person>();
      List<Person> authors2 = new ArrayList<Person>();

      authors1.add(person1);
      authors1.add(person2);
      authors2.add(person3);
  //    authors2.add(person2);

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

      try 
      {
        pD = tmp.parse(dateSInput);
      } 
      catch (ParseException e) 
      {
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

      try 
      {
        pD = tmp.parse(pubDate);
      } 
      catch (ParseException e) 
      {
        System.out.println("Unparseable.");
      }
      publication2.setEnvisagedPublicationDate(pD);

      entitymanager.persist(publication1);
      entitymanager.persist(publication2);

      entitymanager.getTransaction().commit();
    }

    @After
    public void closeManager() 
    {
      entitymanager.close();
      emfactory.close();
    }
    
}
