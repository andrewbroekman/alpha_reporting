package com.codinginfinity.research.report.defaultImpl;

import com.codinginfinity.research.report.GetAccreditationUnitReportRequest;
import com.codinginfinity.research.report.GetAccreditationUnitReportResponse;
import com.codinginfinity.research.report.GetProgressReportRequest;
import com.codinginfinity.research.report.GetProgressReportResponse;
import com.codinginfinity.research.report.InvalidRequestException;
import com.codinginfinity.research.report.Reporting;

/**
 *
 * @author 
 */
public class ReportingImpl implements Reporting{

    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest getAccreditationUnitReportRequest) throws InvalidRequestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GetProgressReportResponse getProgressReport(GetProgressReportRequest getProgressReportRequest) throws InvalidRequestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
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
        
        //TO-DO
        //Finish implementation
        return "";
    }
    
}
