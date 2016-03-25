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
package com.codinginfinity.research.report;

public class ReportingMock implements Reporting{

    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest getAccreditationUnitReportRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GetProgressReportResponse getProgressReport(GetProgressReportRequest getProgressReportRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
