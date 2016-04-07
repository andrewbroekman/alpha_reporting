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
    
    @Test
    public void progressReportTest1() {
               
        
        
        
        assertTrue("SVG was printed",res.getSVG());
        assertTrue("PDF was printed",res.getPDF());
        assertTrue("HTML was printed", res.getHTML());
        
        
       
        
    }
    
    @Inject
    private ReportingImpl ReportingMock;
    @Inject
    private GetProgressReportRequest RequestMock;
    
    private GetProgressReportResponse res;
    
    
    
}
