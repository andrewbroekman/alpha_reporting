/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codinginfinity.research.report;

import com.codinginfinity.research.people.ReqEntity;
import com.codinginfinity.research.publication.PublicationType;
import com.codinginfinity.research.report.defaultImpl.ReportingImpl;
import com.codinginfinity.research.report.defaultImpl.GetProgressReportRequest;
import com.codinginfinity.research.report.defaultImpl.GetAccreditationUnitReportRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Nsovo
 */
@Configuration
class ReportingTestConfiguration {
    @Bean
    public ReportingImpl ReportingMock(){        
        return new ReportingImpl();
    }
    
    /**
     * 
     * @return 
     */
    @Bean
    public GetAccreditationUnitReportRequest ErrorAccUnitReportRequestMock(){        
        return new GetAccreditationUnitReportRequest();
    }
    
    //============================================================================================
    //============================================================================================
    
    /**
     * 
     * @return 
     */
    @Bean
    public GetProgressReportRequest ErrorProgressReportRequestMock(){        
        return new GetProgressReportRequest();
    }
    
    /**
     * 
     * @return 
     */
    @Bean
    public GetProgressReportRequest PersonEntityProgRequestMock()
    {
        ReqEntity e  = new ReqEntity("PERSON", "Dave");
               
        return new GetProgressReportRequest(e);
    }
    
    @Bean
    public GetProgressReportRequest GroupEntityProgRequestMock()
    {
        ReqEntity e  = new ReqEntity("GROUP", "Test Research Group 1");
               
        return new GetProgressReportRequest(e);
    }
    
    @Bean
    public GetProgressReportRequest PubTypeProgRequestMock()
    {
        PublicationType t = new PublicationType();
        t.setName("Journal");
        t.setAccreditationPoints(1.0); 
               
        return new GetProgressReportRequest(t);
    }
    
    @Bean
    public GetProgressReportRequest GroupTypeProgRequestMock()
    {
        ReqEntity e  = new ReqEntity("GROUP", "Test Research Group 2");
        PublicationType t = new PublicationType();
        t.setName("Journal");
        t.setAccreditationPoints(1.0); 
               
        return new GetProgressReportRequest(e,t);
    }
    
     @Bean
     public GetProgressReportRequest PersonTypeProgRequestMock()
    {
        ReqEntity e  = new ReqEntity("PERSON", "sally");
        PublicationType t = new PublicationType();
        t.setName("Journal");
        t.setAccreditationPoints(1.0); 
               
        return new GetProgressReportRequest(e,t);
    }
    
}
