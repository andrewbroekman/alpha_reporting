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
//import com.codinginfinity.research.publication.PublicationType;
import com.codinginfinity.research.publication.LifeCycleState;
import com.codinginfinity.research.publication.Period;
import java.util.List;
import javax.inject.Inject;

import javax.persistence.*;
import net.sf.jasperreports.engine.data.ListOfArrayDataSource;
public class ReportingImpl implements Reporting{
    
    EntityManagerFactory emfactory ;//= Persistence.createEntityManagerFactory("ReportingTestUnit");
    
    EntityManager entitymanager;
    public ReportingImpl(EntityManagerFactory emfactory,  EntityManager entitymanager){
        this.emfactory = emfactory;
        this.entitymanager = entitymanager;
    }
    
    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest request) throws InvalidRequestException
    {
        if(request != null)
        {
            Query query = generateAccreditationQuery(request);
            
            return buildAccreditationReport(query);


        }
        else
        {
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
    Query generateAccreditationQuery(GetAccreditationUnitReportRequest request) throws InvalidRequestException{
        ReqEntity entity = request.getEntity();
        LifeCycleState state = request.getLifeCycleState();

        com.codinginfinity.research.publication.PublicationType type = null; //request.getPublicationType();
        Period period = request.getPeriod();
        Query query = null;

        if(entity != null)
        {
           if(state == null && type == null && period == null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query = entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity").setParameter("entity",entity.getName());
               }
               else
               {
                   query= entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity").setParameter("entity",entity.getName());
               }
           }
            else if(state != null && type == null && period == null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query= entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state ").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState());
               }
               else
               {
                   query= entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState());

               }

           }
            else if(state == null && type != null && period == null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query= entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.publicationType.name = :type").setParameter("entity",entity.getName()).setParameter("type", type.getName());
               }
               else
               {
                   query= entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.publicationType.name = :type").setParameter("entity",entity.getName()).setParameter("type", type.getName());
               }
           }

           else if(state == null && type == null && period != null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query= entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
               else
               {
                   query= entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                            " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                            "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
           }

           else if(state != null && type != null && period == null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query= entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.publicationType.name = :type" +
                           "AND b.lifeCycleState.lifeCycleState = :state").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName());
               }
               else
               {
                   query= entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.lifeCycleState.lifeCycleState = :state").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName());

               }
           }

           else if(state != null && type == null && period != null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query = entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
               else
               {
                   query = entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
           }

           else if(state == null && type != null && period != null)
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query = entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
               else
               {
                   query = entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.envisagedPublicationDate BETWEEN :start " +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
           }
           //nothing is null
            else
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   query =entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
               else
               {
                   query = entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
           }
        }
        //entity is null
        else
        {
            if(state != null && type == null && period == null)
            {
                query =  entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.lifeCycleState.lifeCycleState = :state").setParameter("state", state.getLifeCycleState());
            }
            else if(state == null && type != null && period == null)
            {
                query = entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = :type").setParameter("type", type.getName());
            }

            else if(state == null && type == null && period != null)
            {
                query = entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.envisagedPublicationDate BETWEEN :start" +
                        " AND :end").setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
            }

            else if(state != null && type != null && period == null)
            {
                query= entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = :type " +
                        "AND b.lifeCycleState.lifeCycleState = :state ").setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName());
            }

            else if(state != null && type == null && period != null)
            {
                query= entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.lifeCycleState.lifeCycleState = :state " +
                        "AND b.envisagedPublicationDate BETWEEN :start" +
                        " AND :end").setParameter("state", state.getLifeCycleState()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
            }


            else if(state == null && type != null && period != null)
            {
                query= entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = :type " +
                        "AND b.envisagedPublicationDate BETWEEN :start" +
                        " AND :end").setParameter("start",period.getStartDate()).setParameter("end",period.getEndDate()).setParameter("type",type.getName());
            }

            //If everything is null
            else if(state == null && type == null && period == null)
            {
                throw new InvalidRequestException();
            }
            //state, type and period are all not null
            else
            {
                query= entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = :type " +
                        "AND b.lifeCycleState.lifeCycleState = :state " +
                        "AND b.envisagedPublicationDate BETWEEN  :start" +
                        " AND  + :end").setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
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
            Query query = generateProgressReportQuery(request);
            return buildProgressReport(query);
        }
        
    }
       
    private Query generateProgressReportQuery( GetProgressReportRequest request ){
        ReqEntity entity = request.getEntity();
        com.codinginfinity.research.report.defaultImpl.PublicationType pubtype = request.getPublicationType();
              
        if(entity == null)
        {
            System.out.println("ENTITY = NULL");
            return  entitymanager.createQuery("SELECT p.title TITLE, p.lifeCycleState.misc PROGRESS FROM Publication p "
                    + "WHERE p.lifeCycleState.lifeCycleState = :inProgress AND p.publicationType.name = :type").setParameter("inProgress", "InProgress").setParameter("type", pubtype.getName());
           
        }
        else if(pubtype == null)
        {
            System.out.println("PUBTYPE = NULL");
            String type = entity.getType();
            String name = entity.getName();
            
            if("group".equalsIgnoreCase(type)){
                
                
               return  entitymanager.createQuery("SELECT p.title TITLE, p.lifeCycleState.misc PROGRESS FROM Publication p "
                       +"INNER JOIN p.group g WHERE g.name = :group "
                       +"AND p.lifeCycleState.lifeCycleState = :inProgress").setParameter("group", name).setParameter("inProgress", "InProgress");
           
            }
            else {    
                   return  entitymanager.createQuery("SELECT p.title TITLE, p.lifeCycleState.misc PROGRESS FROM Publication p "
                       +"INNER JOIN p.authors a WHERE a.firstNames = :firstnames "
                       +"AND p.lifeCycleState.lifeCycleState = :inProgress").setParameter("firstnames", name).setParameter("inProgress", "InProgress");
                   
                
            }
        }
        else
        {
            
            String type = entity.getType();
            String name = entity.getName();
            
            if("group".equalsIgnoreCase(type)){
                
                return  entitymanager.createQuery("SELECT p.title AS TITLE, p.lifeCycleState.misc AS PROGRESS FROM Publication p "
                       +"INNER JOIN p.group g WHERE g.name = :group "
                       +"AND p.lifeCycleState.lifeCycleState = :inProgress AND p.publicationType.name = :type").setParameter("group", name).setParameter("inProgress", "InProgress").setParameter("type", pubtype.getName());
            }
            else {
                
                return  entitymanager.createQuery("SELECT p.title AS TITLE, p.lifeCycleState.misc AS PROGRESS FROM Publication p "
                       +"INNER JOIN p.authors a WHERE a.firstNames = :firstnames "
                       +"AND p.lifeCycleState.lifeCycleState = :inProgress AND p.publicationType.name = :type").setParameter("firstnames", name).setParameter("inProgress", "InProgress").setParameter("type", pubtype.getName());
                
            }            
        }  
        
    }
    
    private GetProgressReportResponse buildProgressReport(Query q){
        try
        {
            JasperPrint jasperPrint;
            InputStream input = new FileInputStream(new File("src/main/java/com/codinginfinity/research/report/defaultImpl/ProgressReportTemplate.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);
            Map parameters = new HashMap();
            parameters.put("DETAILS", "Progress report from DATE to DATE");
            parameters.put("DETAILS", "Progress report from DATE to DATE");

            GetProgressReportResponse response;
            List<Object[]> list = (List<Object[]>) q.getResultList();
            
            String[] a = {"TITLE","PROGRESS"};
            JRDataSource ColDataSource  = new ListOfArrayDataSource(list, a);

            
            jasperPrint = JasperFillManager.fillReport(report, parameters, ColDataSource);

            response = new GetProgressReportResponse(jasperPrint);
            return response;
        }
        catch(FileNotFoundException | JRException e)
        {
            System.out.println("HERE IS THE ERROR:  " + e);
            return null;
        }
    }
    
}  
