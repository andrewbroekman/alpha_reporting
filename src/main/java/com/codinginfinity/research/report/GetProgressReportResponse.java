/**
*	@file GetProgressReportResponse.java
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An object that represents a completed GetProgressReportRequest
*	@section Description
*	This object is what is returned once a reporting request is complete. It contains the generated reports based off of the data sent in the GetProgressReportRequest object.
*	The report returned deals specifically with the progress status of publications.
*/

package com.codinginfinity.research.report;
import net.sf.jasperreports.engine.JasperPrint;
import org.w3c.dom.Document;

public class GetProgressReportResponse implements Response{
    
    /**
     * Assigns the JasperPrint file. This file allows us to generate XML, PDF and SVG formats of the report
     * @param print
     */
    public GetProgressReportResponse(JasperPrint print) {
        super();
        this.print = print;
    }    
   
   /* public Document getSVG(){
        return svg;        
    }
     public PDF getPDF(){
        return pdf;        
    }
    
    public XML getXML(){
        return xml;        
    }*/
    
    private final JasperPrint print;    
}
