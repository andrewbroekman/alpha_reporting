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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
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
    
    /**
     * 
     */
    public void getSVG(){
        //return svg;        
    }
    
    /**
     * 
     */
    public void getPDF(){
        try{ 
            
            JasperExportManager.exportReportToPdfFile(print, "reporting.pdf");
           
        }
        catch( JRException e){
            System.err.println( "JRException " + e);
        }
        
        //return pdf;        
    }
    
    /**
     * 
     */
    public void getXML(){
        try{ 
            JasperExportManager.exportReportToXmlFile(print, "reporting.xml", true);
            
        }
        catch( JRException e){
            System.err.println( "JRException " + e);
        }
        
       
        //return xml;        
    }
    
    /**
     * 
     */
    public void getHTML(){
        try{ 
            JasperExportManager.exportReportToHtmlFile(print, "reporting.xml");
            
        }
        catch( JRException e){
            System.err.println( "JRException " + e);
        }
        //return HtmlFile
    }
    
    private final JasperPrint print;    
}
