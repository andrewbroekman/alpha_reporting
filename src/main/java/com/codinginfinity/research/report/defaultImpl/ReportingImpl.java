/**
 *	@file ReportingImpl.java
 *      @class ReportingImpl
 *	@author COS301 Reporting Alpha Team
 *	@version 1.0 alpha
 *	@brief Am implementation of the Reporting.java interface
 *	@section Description 
 */
package com.codinginfinity.research.report.defaultImpl;

import com.codinginfinity.research.report.InvalidRequestException;
import com.codinginfinity.research.report.Reporting;
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

import com.codinginfinity.research.people.ReqEntity;
import com.codinginfinity.research.publication.Publication;
import com.codinginfinity.research.publication.PublicationType;
import com.codinginfinity.research.publication.LifeCycleState;
import com.codinginfinity.research.publication.Period;

import javax.persistence.*;
public class ReportingImpl implements Reporting{
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ReportingTestUnit");
    EntityManager entitymanager = emfactory.createEntityManager();

    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest request) throws InvalidRequestException
    {
        if(request != null)
        {
            Query query = entitymanager.createQuery(generateAccreditationQuery(request));
            entitymanager.close();
            emfactory.close();
            return buildAccreditationReport(query);


        }
        else
        {
            entitymanager.close();
            emfactory.close();
            throw new InvalidRequestException();
        }
    }

    private GetAccreditationUnitReportResponse buildAccreditationReport(Query q){
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
    String generateAccreditationQuery(GetAccreditationUnitReportRequest request) throws InvalidRequestException{
        ReqEntity entity = request.getEntity();
        LifeCycleState state = request.getLifeCycleState();

        PublicationType type = request.getPublicationType();
        Period period = request.getPeriod();
        String query = null;

        if(entity != null)
        {
           if(state == null && type == null && period == null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = \"entity.getName()\" ";
               }
               else
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" ";
               }
           }
            else if(state != null && type == null && period == null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query =  "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = \"entity.getName()\" " +
                           "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" ";
               }
               else
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" " +
                           "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" ";

               }

           }
            else if(state == null && type != null && period == null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query ="SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = \"entity.getName()\" " +
                           "AND b.publicationType.name = \"type.getName()\"";
               }
               else
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" " +
                           "AND b.publicationType.name = \"type.getName()\"";
               }
           }

           else if(state == null && type == null && period != null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" " +
                           "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                           " AND " + period.getEndDate();
               }
               else
               {
                   query =  "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                            " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                            "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" " +
                           "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                           " AND " + period.getEndDate();
               }
           }

           else if(state != null && type != null && period == null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query =  "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = \"entity.getName()\" " +
                           "AND b.publicationType.name = \"type.getName()\"" +
                           "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" ";
               }
               else
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" " +
                           "AND b.publicationType.name = \"type.getName()\"" +
                           "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" ";

               }
           }

           else if(state != null && type == null && period != null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = \"entity.getName()\" " +
                           "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" " +
                           "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                           " AND " + period.getEndDate();
               }
               else
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" " +
                           "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" " +
                           "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                           " AND " + period.getEndDate();
               }
           }

           else if(state == null && type != null && period != null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = \"entity.getName()\" " +
                           "AND b.publicationType.name = \"type.getName()\"" +
                           "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                           " AND " + period.getEndDate();
               }
               else
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" " +
                           "AND b.publicationType.name = \"type.getName()\"" +
                           "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                           " AND " + period.getEndDate();
               }
           }
           //nothing is null
            else
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = \"entity.getName()\" " +
                           "AND b.publicationType.name = \"type.getName()\"" +
                           "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" " +
                           "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                           " AND " + period.getEndDate();
               }
               else
               {
                   query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = \"entity.getName()\" " +
                           "AND b.publicationType.name = \"type.getName()\"" +
                           "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" " +
                           "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                           " AND " + period.getEndDate();
               }
           }
        }
        //entity is null
        else
        {
            if(state != null && type == null && period == null)
            {
                query =  "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.lifeCycleState.lifeCycleState = \"state.getLifeCycleState()\"";
            }
            else if(state == null && type != null && period == null)
            {
                query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = \"type.getName()\"";
            }

            else if(state == null && type == null && period != null)
            {
                query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                        " AND " + period.getEndDate();
            }

            else if(state != null && type != null && period == null)
            {
                query =  "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = \"type.getName()\"" +
                        "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" ";
            }

            else if(state != null && type == null && period != null)
            {
                query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.lifeCycleState.lifeCycleState = \"state.getName()\" " +
                        "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                        " AND " + period.getEndDate();
            }


            else if(state == null && type != null && period != null)
            {
                query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = \"type.getName()\"" +
                        "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                        " AND " + period.getEndDate();
            }

            //If everything is null
            else if(state == null && type == null && period == null)
            {
                throw new InvalidRequestException();
            }
            //state, type and period are all not null
            else
            {
                query = "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = \"type.getName()\"" +
                        "AND b.lifeCycleState.lifeCycleState = \"state.getName()\" " +
                        "AND b.envisagedPublicationDate BETWEEN " + period.getStartDate() +
                        " AND " + period.getEndDate();
            }

        }
        return query;
    }
    
    //==================================================================================================================
    //==================================================================================================================

    @Override
    public GetProgressReportResponse getProgressReport(GetProgressReportRequest request) throws InvalidRequestException {
        if(request == null){
            throw new InvalidRequestException();
        }
        else if(request.getEntity() == null && request.getPublicationType()==null)
        {
            throw new InvalidRequestException();
        }
        else
        {
            Query query = entitymanager.createQuery(generateProgressReportQuery(request));
            return buildProgressReport(query);
        }
        
    }
       
    private String generateProgressReportQuery( GetProgressReportRequest request ){
        ReqEntity entity = request.getEntity();
        PublicationType pubtype = request.getPublicationType();
        String query = null;
        
        if(entity == null)
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
            System.out.println("HERE 1---------------------------------------------------------------");
            String type = entity.getType();
            String name = entity.getName();
            
            if("group".equalsIgnoreCase(type)){
                System.out.println("HERE 2------------------------------------------------------");
                
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
                System.out.println("HERE 3 --------------------------------------------");
                String r = "SELECT p.title as TITLE, p.misc as PROGRESS, r.name as RESEARCH_GROUP FROM PUBLICATION p INNER JOIN PERSON u.personid INNER JOIN RESEARCHGROUP r.groupid WHERE p.lifecyclestate = 'InProgress' AND u.firstnames = 'Dave' GROUP BY p.title"; 
                        
                       
                return  r;/*"SELECT p.title as TITLE, p.misc as PROGRESS, r.name as RESEARCH_GROUP"
                   +" FROM PUBLICATION p "
                   +" INNER JOIN PUBLICATION_PERSON s ON p.publicationid = s.publication_publicationid "
                   +" INNER JOIN PERSON u ON u.personid = s.authors_personid "
                   +" INNER JOIN RESEARCHGROUP r ON r.groupid = u.group_groupid "
                   +" WHERE p.lifecyclestate = 'InProgress' " 
                   +" AND u.firstnames = '" + name + "' "
                   +" GROUP BY p.title" ;*/
            }
        }
        else
        {
            
            String type = entity.getType();
            String name = entity.getName();
            
            if("group".equalsIgnoreCase(type)){
                
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
    
    private GetProgressReportResponse buildProgressReport(Query q){
        try
        {
            JasperPrint jasperPrint;
            InputStream input = new FileInputStream(new File("./ProgressReportTemplate.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);
            Map parameters = new HashMap();
            parameters.put("DETAILS", "Progress report from DATE to DATE");
            parameters.put("DETAILS", "Progress report from DATE to DATE");

            GetProgressReportResponse response;
            ArrayList<Publication> list = (ArrayList<Publication>) q.getResultList();

            JRBeanCollectionDataSource ColDataSource = new JRBeanCollectionDataSource(list);
            jasperPrint = JasperFillManager.fillReport(report, parameters, ColDataSource);

            response = new GetProgressReportResponse(jasperPrint);
            return response;
        }
        catch(FileNotFoundException | JRException e)
        {
            return null;
        }
    }
    
}  
