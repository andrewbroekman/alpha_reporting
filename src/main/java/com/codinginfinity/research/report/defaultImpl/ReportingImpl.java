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

import com.codinginfinity.research.people.*;
import com.codinginfinity.research.report.*;
import com.codinginfinity.research.publication.*;

public class ReportingImpl implements Reporting
{

    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest request) throws InvalidRequestException
    {
        if(request != null)
        {

        }
        else
            throw new InvalidRequestException();
        return null;
    }

    /**
     * Generates the correct JPQL query according to the request that is passed in
     * @return string containing the JPQL query to be executed
     */
    public String generateAccreditationQuery(GetAccreditationUnitReportRequest request)
    {
        ReqEntity entity = request.getEntity();
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
    
}
