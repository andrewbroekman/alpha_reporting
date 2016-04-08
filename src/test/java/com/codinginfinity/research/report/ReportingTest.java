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
    
    
    
}
