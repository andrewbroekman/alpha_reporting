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
import org.w3c.dom.Document;

public class GetProgressReportResponse implements Response{
    
    /**
     * Assigns the SVG file
     * @param svg
     */
    public GetProgressReportResponse(Document svg/*, PDF pdf, XML xml*/) {
        super();
        this.svg = svg;
        //this.pdf = pdf;
       // this.xml = xml;
    }    
   
    public Document getSVG(){
        return svg;        
    }
    /* public PDF getPDF(){
        return pdf;        
    }
    
    public XML getXML(){
        return xml;        
    }*/
    
    private final Document svg;
    //private PDF pdf;
    //private XML xml;
    
    
    
}
