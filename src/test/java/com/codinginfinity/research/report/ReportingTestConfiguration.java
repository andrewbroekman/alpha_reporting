/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codinginfinity.research.report;

import com.codinginfinity.research.report.defaultImpl.ReportingImpl;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Nsovo
 */
class ReportingTestConfiguration {
     @Bean
    public ReportingImpl ReportingMock()
    {
        
        return new ReportingImpl();
    }
    
     @Bean
    public GetProgressReportRequest RequestMock()
    {
        
        return null;
    }
    
}
