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
import org.w3c.dom.Document;

public class ReportingMock implements Reporting{
    
    /**
     * 
     * @param getAccreditationUnitReportRequest
     * @return GetAccreditationUnitReportResponse
     */
    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest getAccreditationUnitReportRequest) throws InvalidRequestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Implementation of the getProgressReport service contract
     * @param request
     * @return GetProgressReportResponse
     * @throws InvalidRequestException
     */
    @Override
    public GetProgressReportResponse getProgressReport(GetProgressReportRequest request) throws InvalidRequestException{
        //Validate request
        //if(request.getEntity() == null  && request.getPublicationType == null)
            //throw new InvalidRequestException();  
        return progressResponse;
    }
    
     /**
     * Sets the response object. Allows tester to change response object at will
     * @param res 
     */
    public void setProgressReportRes(GetProgressReportResponse res){
        this.progressResponse = res;
    }
       
    private GetProgressReportResponse progressResponse;   
}
