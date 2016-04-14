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
//import com.codinginfinity.research.publication.LifeCycleState;
//import com.codinginfinity.research.publication.Period;
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
    /**
     * Implementation of the  GetAccreditationUnit service contract
     * @param request
     * @return
     * @throws InvalidRequestException 
     */
    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest request) throws InvalidRequestException
    {
        if(request == null){
            throw new InvalidRequestException();
        }
        else if(request.getEntity() == null && request.getPublicationType()==null && request.getPeriod() == null && request.getLifeCycleState() == null)
        {
            throw new InvalidRequestException();
        }
        else
        {
            Query query = generateAccreditationQuery(request);
            
            return buildAccreditationReport(query);


        }
       
    }
    /**
     * Builds the Get Accreditation Unit response object for the service contract
     * @param q
     * @return 
     */
    private GetAccreditationUnitReportResponse buildAccreditationReport(Query q){
        try
        {
            JasperPrint jasperPrint;
            InputStream input = new FileInputStream(new File("src/main/java/com/codinginfinity/research/report/defaultImpl/AccreditationUnitReportTemplate.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);
            Map parameters = new HashMap();
            parameters.put("DETAILS", "Progress report from DATE to DATE");
            parameters.put("DETAILS", "Progress report from DATE to DATE");

            GetAccreditationUnitReportResponse response;
           List<Object[]> list = (List<Object[]>) q.getResultList();
            
            String[] a = {"ACCUNITS", "REPORT_TITLE", "REPORT_TYPE", "STATE"};
            JRDataSource ColDataSource  = new ListOfArrayDataSource(list, a);
            jasperPrint = JasperFillManager.fillReport(report, parameters, ColDataSource);

            response = new GetAccreditationUnitReportResponse(jasperPrint);
            return response;
        }
        catch(FileNotFoundException | JRException e)
        {
            System.out.println(e + "   111111111111111111");
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
        com.codinginfinity.research.report.defaultImpl.PublicationType type = request.getPublicationType();
        Period period = request.getPeriod();
        
       if(state == null && type == null && period == null && entity != null)
       {
           if("person".equalsIgnoreCase(entity.getType()))
           {
               System.out.println("HERE Person " + entity.getName());
               return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints ACCUNITS, b.title AS REPORT_TITLE, " +
                          " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity").setParameter("entity","Sally");
            }
            else
            {
                System.out.println("HERE Group " + entity.getName());
                return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints UNITS, b.title REPORT_TITLE, " +
                           "b.publicationType.name REPORT_TYPE, b.lifeCycleState.lifeCycleState STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity").setParameter("entity",entity.getName());
            }
       }
       else if(entity == null && type == null && period ==null && state != null)
       {
           return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.lifeCycleState.lifeCycleState = :state").setParameter("state", state.getLifeCycleState());
           
       }
       else if(entity == null && state == null && period ==null && type != null){
           
           System.out.println("HERE 17");
                return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = :type").setParameter("type", type.getName());
           
       }
       else if(entity == null && state == null && type ==null && period != null){
           
           
           System.out.println("HERE 18");
                return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.envisagedPublicationDate BETWEEN :start" +
                        " AND :end").setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
           
       }
       else if(entity != null && state != null && type == null && period == null){
            if("person".equalsIgnoreCase(entity.getType()))
            {
                System.out.println("HERE 3");
                return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state ").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState());
            }
            else
            {
                System.out.println("HERE 4");
                return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState());

            }
       }
       
       
       else if(entity != null && state == null && type != null && period == null){
            if("person".equalsIgnoreCase(entity.getType()))
            {
                System.out.println("HERE 3");
                return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.publicationType.name = :type").setParameter("entity",entity.getName()).setParameter("type", type.getName());
            }
            else
            {
                System.out.println("HERE 4");
                return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState());

            }
       }
       
       
       
       else if(entity != null && state == null && type == null && period != null){
           if("person".equalsIgnoreCase(entity.getType()))
               {
                   System.out.println("HERE 7");
                   return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
               else
               {
                   System.out.println("HERE 8");
                   return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                            " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                            "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
       }
       else if(entity == null && state == null && type != null && period != null){
           
           System.out.println("HERE 21");
                return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = :type " +
                        "AND b.envisagedPublicationDate BETWEEN :start" +
                        " AND :end").setParameter("start",period.getStartDate()).setParameter("end",period.getEndDate()).setParameter("type",type.getName());
       }
       else if(entity == null && state != null && type != null && period == null){
           System.out.println("HERE 19");
                return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.publicationType.name = :type " +
                        "AND b.lifeCycleState.lifeCycleState = :state ").setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName());
       } else if(entity == null && state != null && type == null && period != null)
            {
                System.out.println("HERE 20");
                return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                        " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                        "FROM  Publication b WHERE b.lifeCycleState.lifeCycleState = :state " +
                        "AND b.envisagedPublicationDate BETWEEN :start" +
                        " AND :end").setParameter("state", state.getLifeCycleState()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
            }      
       else if(entity != null && state != null && type != null && period == null){
           if("person".equalsIgnoreCase(entity.getType()))
               {
                   System.out.println("HERE 9");
                   return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           "b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.lifeCycleState.lifeCycleState = :state").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName());
               }
               else
               {
                   System.out.println("HERE 10");
                   return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.lifeCycleState.lifeCycleState = :state").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName());

               }
       }       
       else if(entity != null && state == null && type != null && period != null){
           if("person".equalsIgnoreCase(entity.getType()))
               {
                   System.out.println("HERE 13");
                   return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
               else
               {
                   System.out.println("HERE 14");
                   return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.envisagedPublicationDate BETWEEN :start " +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
       
       }else if(entity != null && state != null && type == null && period != null){
           if("person".equalsIgnoreCase(entity.getType()))
               {
                   System.out.println("HERE 11");
                   return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
               else
               {
                   System.out.println("HERE 12");
                   return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
       }else if(entity == null && state != null && type != null && period != null){
           return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b WHERE " +
                           "b.publicationType.name = :type " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
       }       
       else
           {
               if("person".equalsIgnoreCase(entity.getType()))
               {
                   System.out.println("HERE 14");
                   return entitymanager.createQuery( "SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.authors p WHERE p.firstNames = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
               else
               {
                   System.out.println("HERE 15");
                   return entitymanager.createQuery("SELECT b.publicationType.accreditationPoints AS UNITS, b.title AS REPORT_TITLE, " +
                           " b.publicationType.name AS REPORT_TYPE, b.lifeCycleState.lifeCycleState AS STATE " +
                           "FROM  Publication b INNER JOIN b.group g WHERE g.name = :entity " +
                           "AND b.publicationType.name = :type " +
                           "AND b.lifeCycleState.lifeCycleState = :state " +
                           "AND b.envisagedPublicationDate BETWEEN :start" +
                           " AND :end").setParameter("entity",entity.getName()).setParameter("state", state.getLifeCycleState()).setParameter("type", type.getName()).setParameter("start",period.getStartDate()).setParameter("end", period.getEndDate());
               }
           }        
    
    }
    
    //==================================================================================================================
    //==================================================================================================================
    /**
     * Implementation of the GetProgressReportResponse for the service contract
     * @param request
     * @return
     * @throws InvalidRequestException 
     */
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
    /**
     * Builds queries to obtain data from the the database to build the GetProgressReportResponse
     * @param request
     * @return 
     */   
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
    /**
     * Builds the GetProgressReportResponse for the service contract 
     * @param q
     * @return 
     */
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
