/**
*	@file GetProgressReportResponse.java
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An object that represents a completed GetProgressReportRequest
*	@section Description
*	This object is what is returned once a reporting request is complete. It contains the generated reports based off of the data sent in the GetProgressReportRequest object.
*	The report returned deals specifically with the progress status of publications.
*/

package com.codinginfinity.research.report.defaultImpl;
import com.codinginfinity.research.report.Response;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetProgressReportResponse implements Response
{
    private static final long serialVersionUID = 1L;
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
     * @return 
     */
    public boolean getSVG(){
        Document document;
        try{
            DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
                document = domImpl.createDocument(null, "svg", null);
                ImageHandler v = new DefaultImageHandler();
                ExtensionHandler ex = new DefaultExtensionHandler();
                SVGGraphics2D grx = new SVGGraphics2D(document, v, ex, true);

                JRGraphics2DExporter exporter = new JRGraphics2DExporter();

               exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
               exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, grx);
               exporter.setParameter(JRExporterParameter.PAGE_INDEX, 0);
               exporter.exportReport();

                grx.stream(new FileWriter(new File("ProgressReport.svg")), true);
        }
        catch(JRException | IOException e){
            System.out.println( e);
            return false;
        }
        return true;             
    }
    
    /**
     * 
     * @return 
     */
    public boolean getPDF(){
        try{ 
            
            JasperExportManager.exportReportToPdfFile(print, "ProgressReport.pdf");
           
        }
        catch( JRException e){
            System.out.println( "JRException " + e);
            return false;
        }
        return true;
        //return pdf;        
    }
    
    /**
     * 
     * @return 
     */
    public String getXML(){
        String result="";
        try{ 
            result=JasperExportManager.exportReportToXml(print);
            
        }
        catch( JRException e){
            System.err.println( "JRException " + e);
        }
        return result;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getHTML(){
        try{ 
            JasperExportManager.exportReportToHtmlFile(print, "ProgressReport.html");
            
        }
        catch( JRException e){
            System.err.println( "JRException " + e);
            return false;
        }
        return true;
        //return HtmlFile
    }
    
    private final JasperPrint print;    
}
