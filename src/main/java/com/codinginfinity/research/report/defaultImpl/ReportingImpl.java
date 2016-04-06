/**
 *	@file ReportingMock.java
 *       @class Reporting
 *	@author COS301 Reporting Alpha Team
 *	@version 1.0 alpha
 *	@brief A mock object that mocks the generated reports
 *	@section Description
 * 	This class will mock the two service contracts provided by Reporting interface.
 *
 */




package com.codinginfinity.research.report.defaultImpl;

import com.codinginfinity.research.report.GetAccreditationUnitReportRequest;
import com.codinginfinity.research.report.GetAccreditationUnitReportResponse;
import com.codinginfinity.research.report.GetProgressReportRequest;
import com.codinginfinity.research.report.GetProgressReportResponse;
import com.codinginfinity.research.report.InvalidRequestException;
import com.codinginfinity.research.report.Reporting;

import com.codinginfinity.research.people.Entity;
import com.codinginfinity.research.publication.*;

import javax.persistence.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author 
 */
public class ReportingImpl implements Reporting{
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PersistenceUnit");
    EntityManager entitymanager = emfactory.createEntityManager();
    
    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest request) throws InvalidRequestException
    {
        if(request != null)
        {
            Query query = entitymanager.createQuery(generateAccreditationQuery(request));
            return buildAccreditationReport(query);
        }
        else
            throw new InvalidRequestException();
    }

    private GetAccreditationUnitReportResponse buildAccreditationReport(Query q)
    {
        try
        {
            JasperPrint jasperPrint;
            InputStream input = new FileInputStream(new File("./AccreditationUnitReport.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);
            Map parameters = new HashMap();
            parameters.put("DETAILS", "Progress report from DATE to DATE");
            parameters.put("DETAILS", "Progress report from DATE to DATE");

            GetAccreditationUnitReportResponse response;
            ArrayList<Publication> list = (ArrayList<Publication>) q.getResultList();

            JRBeanCollectionDataSource ColDataSource = new JRBeanCollectionDataSource(list);
            jasperPrint = JasperFillManager.fillReport(report, parameters, ColDataSource);

            response = new GetAccreditationUnitReportResponse(jasperPrint);
            return response;
        }
        catch(FileNotFoundException | JRException e)
        {
            return null;
        }
    }
    
    /**
     * Generates the correct JPQL query according to the request that is passed in
     * @return string containing the JPQL query to be executed
     */
    String generateAccreditationQuery(GetAccreditationUnitReportRequest request) throws InvalidRequestException
    {
        Entity entity = request.getEntity();
        LifeCycleState state = request.getLifeCycleState();
        PublicationType type = request.getPublicationType();
        Period period = request.getPeriod();
        String query = null;

        if(entity != null)
        {
           if(state == null && type == null && period == null)
           {
               if(entity.getType() == "person")
               {
                   query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                           "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                           "FROM PUBLICATION_PERSON a JOIN PUBLICATION b " +
                           "ON a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID JOIN " +
                           "PERSON p on a.AUTHORS_PERSONID = p.PERSONID" +
                           " WHERE p.FIRSTNAMES = " + entity.getName();
               }
               else
               {
                   query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE " +
                           "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                           "FROM PUBLICATION_PERSON a " +
                           "JOIN PUBLICATION b on a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID " +
                           "JOIN PERSON p ON a.AUTHORS_PERSONID = p.PERSONID JOIN RESEARCHGROUP g ON g.GROUPID = p.GROUP_GROUPID " +
                           "WHERE g.NAME = " + entity.getName();
               }
           }
            else if(state != null && type == null && period == null)
           {
               if(entity.getType() == "person")
               {
                   query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                           "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                           "FROM PUBLICATION_PERSON a JOIN PUBLICATION b " +
                           "ON a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID JOIN " +
                           "PERSON p on a.AUTHORS_PERSONID = p.PERSONID" +
                           " WHERE p.FIRSTNAMES = " + entity.getName() + " AND b.LIFECYCLESTATE = " + "'"+state
                           .getLifeCycleState() +"'";
               }
               else
               {
                   query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                           "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                           "FROM PUBLICATION_PERSON a " +
                           "JOIN PUBLICATION b on a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID " +
                           "JOIN PERSON p ON a.AUTHORS_PERSONID = p.PERSONID JOIN RESEARCHGROUP g ON g.GROUPID = p.GROUP_GROUPID " +
                           "WHERE g.NAME = " + entity.getName() + " AND b.LIFECYCLESTATE = " + "'"+state
                           .getLifeCycleState() +"'";
               }

           }
            else if(state == null && type != null && period == null)
           {
               if(entity.getType() == "person")
               {
                   query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                           "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                           "FROM PUBLICATION_PERSON a JOIN PUBLICATION b " +
                           "ON a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID JOIN " +
                           "PERSON p on a.AUTHORS_PERSONID = p.PERSONID" +
                           " WHERE p.FIRSTNAMES = " + entity.getName() + " AND b.NAME = " + "'"+type.getName() +"'";
               }
               else
               {
                   query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                           "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                           "FROM PUBLICATION_PERSON a " +
                           "JOIN PUBLICATION b on a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID " +
                           "JOIN PERSON p ON a.AUTHORS_PERSONID = p.PERSONID JOIN RESEARCHGROUP g ON g.GROUPID = p.GROUP_GROUPID " +
                           "WHERE g.NAME = " + entity.getName() + " AND b.NAME = " + "'"+type.getName() +"'";
               }
           }
           //Todo: implement this part for period
           else if(state == null && type == null && period != null)
           {}

           else if(state != null && type != null && period == null)
           {
               if(entity.getType() == "person")
               {
                   query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                           "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                           "FROM PUBLICATION_PERSON a JOIN PUBLICATION b " +
                           "ON a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID JOIN " +
                           "PERSON p on a.AUTHORS_PERSONID = p.PERSONID" +
                           " WHERE p.FIRSTNAMES = " + entity.getName() + "AND b.LIFECYCLESTATE = " + "'"+state.getLifeCycleState() +"'" +
                           " AND b.NAME = " + "'"+type.getName() +"'";
               }
               else
               {
                   query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                           "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                           "FROM PUBLICATION_PERSON a " +
                           "JOIN PUBLICATION b on a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID " +
                           "JOIN PERSON p ON a.AUTHORS_PERSONID = p.PERSONID JOIN RESEARCHGROUP g ON g.GROUPID = p.GROUP_GROUPID " +
                           "WHERE g.NAME = " + entity.getName() + "AND b.LIFECYCLESTATE = " + "'"+state.getLifeCycleState() +"'" +
                           " AND b.NAME = " + "'"+type.getName() +"'";
               }
           }
           //Todo: implement this part for period
           else if(state != null && type == null && period != null){}

           //Todo: implement this part for period
           else if(state == null && type != null && period != null)
           {}
            else
           {
               //Todo: implement this part for period
           }
        }
        //entity is null
        else
        {
            if(state != null && type == null && period == null)
            {
                query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                        "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                        "FROM PUBLICATION_PERSON a " +
                        "JOIN PUBLICATION b on a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID " +
                        "JOIN PERSON p ON a.AUTHORS_PERSONID = p.PERSONID JOIN RESEARCHGROUP g ON g.GROUPID = p.GROUP_GROUPID " +
                        "WHERE b.LIFECYCLESTATE = " + "'"+state.getLifeCycleState() +"'";
            }
            else if(state == null && type != null && period == null)
            {
                query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                        "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                        "FROM PUBLICATION_PERSON a " +
                        "JOIN PUBLICATION b on a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID " +
                        "JOIN PERSON p ON a.AUTHORS_PERSONID = p.PERSONID JOIN RESEARCHGROUP g ON g.GROUPID = p.GROUP_GROUPID " +
                        "WHERE b.NAME = " + "'"+type.getName() +"'";
            }
            //Todo: implement this part for period
            else if(state == null && type == null && period != null)
            {}

            else if(state != null && type != null && period == null)
            {
                query = "SELECT b.ACCREDITATIONPOINTS AS UNITS, b.TITLE AS TITLE, " +
                        "b.NAME AS TYPE, b.LIFECYCLESTATE AS STATE " +
                        "FROM PUBLICATION_PERSON a " +
                        "JOIN PUBLICATION b on a.PUBLICATION_PUBLICATIONID = b.PUBLICATIONID " +
                        "JOIN PERSON p ON a.AUTHORS_PERSONID = p.PERSONID JOIN RESEARCHGROUP g ON g.GROUPID = p.GROUP_GROUPID " +
                        "WHERE b.NAME = " + "'"+type.getName() +"'" +
                        " AND b.LIFECYCLESTATE = " + "'" + state.getLifeCycleState() + "'";
            }
            //Todo: implement this part for period
            else if(state != null && type == null && period != null){}

            //Todo: implement this part for period
            else if(state == null && type != null && period != null)
            {}

            //If everything is null
            else if(state == null && type == null && period == null)
            {
                throw new InvalidRequestException();
            }
            //Todo: implement this part for period
            else
            {}

        }
        return query;
    }

    @Override
    public GetProgressReportResponse getProgressReport(GetProgressReportRequest getProgressReportRequest) throws InvalidRequestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *  This function builds the querries for the progress report using the entinty and the public type values
     * @param entity
     * @param pubType
     * @return Query This function returns the query string 
     */
    private String buildProgressReport( Entity entity, PublicationType pubType){
        if(Entity == null)
        {
            
            return "SELECT p.title as TITLE, p.misc as PROGRESS, r.name as RESEARCH_GROUP"
                   +"FROM PUBLICATION p"
                   +"INNER JOIN PUBLICATION_PERSON s ON p.publicationid = s.publication_publicationid  "
                   + "INNER JOIN PERSON u ON u.personid = s.authors_personid"
                   + "INNER JOIN RESEARCHGROUP r ON r.groupid = u.group_groupid"
                   + "WHERE p.lifecyclestate = 'InProgress'" 
                   + "AND p.name = '" + pubtype.getName() + "'"
                   + "GROUP BY p.title" ;
        }
        else if(pubtype == null)
        {
            String type = Entity.getType();
            String name = Entity.getName();
            
            if(type == "group"){
                
           return "SELECT p.title as TITLE, p.misc as PROGRESS, r.name as RESEARCH_GROUP"
                   +"FROM PUBLICATION p"
                   +"INNER JOIN PUBLICATION_PERSON s ON p.publicationid = s.publication_publicationid  "
                   + "INNER JOIN PERSON u ON u.personid = s.authors_personid"
                   + "INNER JOIN RESEARCHGROUP r ON r.groupid = u.group_groupid"
                   + "WHERE p.lifecyclestate = 'InProgress'" 
                   + "AND r.name = '" + name + "'"
                   + "GROUP BY p.title" ;
            }
            else {
                return "SELECT p.title as TITLE, p.misc as PROGRESS, r.name as RESEARCH_GROUP"
                   +"FROM PUBLICATION p"
                   +"INNER JOIN PUBLICATION_PERSON s ON p.publicationid = s.publication_publicationid  "
                   + "INNER JOIN PERSON u ON u.personid = s.authors_personid"
                   + "INNER JOIN RESEARCHGROUP r ON r.groupid = u.group_groupid"
                   + "WHERE p.lifecyclestate = 'InProgress'" 
                   + "AND u.firstnames = '" + name + "'"
                   + "GROUP BY p.title" ;
            }
        }
        else{
            String type = Entity.getType();
            String name = Entity.getName();
            
            if(type == "group"){
                
           return "SELECT p.title as TITLE, p.misc as PROGRESS, p.name AS PUBLICATION_TYPE, r.name as RESEARCH_GROUP"
                   +"FROM PUBLICATION p"
                   +"INNER JOIN PUBLICATION_PERSON s ON p.publicationid = s.publication_publicationid  "
                   + "INNER JOIN PERSON u ON u.personid = s.authors_personid"
                   + "INNER JOIN RESEARCHGROUP r ON r.groupid = u.group_groupid"
                   + "WHERE p.lifecyclestate = 'InProgress'" 
                    + "AND p.name = '" + pubtype.getName() + "'"
                   + "AND r.name = '" + name + "'"
                   + "GROUP BY p.title" ;
            }
            else {
                return "SELECT p.title as TITLE, p.misc as PROGRESS, r.name as RESEARCH_GROUP"
                   +"FROM PUBLICATION p"
                   +"INNER JOIN PUBLICATION_PERSON s ON p.publicationid = s.publication_publicationid  "
                   + "INNER JOIN PERSON u ON u.personid = s.authors_personid"
                   + "INNER JOIN RESEARCHGROUP r ON r.groupid = u.group_groupid"
                   + "WHERE p.lifecyclestate = 'InProgress'" 
                    + "AND p.name = '" + pubtype.getName() + "'"
                   + "AND u.firstnames = '" + name + "'"
                   + "GROUP BY p.title" ;
            }
            
        }
    }
    
    /*Todo: make sure this method will actually work to disconnect from database when the object is destroyed/ when
 programme is closed, otherwise the entityManager should be passed in and closed externally*/
    @Override
    protected void finalize() throws Throwable
    {
        try {
            entitymanager.close();
            emfactory.close();
        } finally {
            super.finalize();
        }
    }   
}
