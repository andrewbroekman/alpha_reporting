/**
*	@file GetAccreditationUnitReportResponse.java
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An object that represents a completed GetAccreditationUnitReportRequest
*	@section Description
*	This object is what is returned once a reporting request is complete. It contains the generated reports based off of the data sent in the GetAccreditationUnitReportRequest object.
*	The returned report will deal specifically with the accreditation units of people, groups, and publications.
*/

package com.codinginfinity.research.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetAccreditationUnitReportResponse implements Response{

    private final JasperPrint print;
    /**
     * Assigns the JasperPrint file. This file allows us to generate XML, PDF and SVG formats of the report
     * @param print
     */
    public GetAccreditationUnitReportResponse(JasperPrint print){
        super();
        this.print = print;
    }

    public void getSVG(){
        try{
            DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
            Document document = domImpl.createDocument(null, "svg", null);
            SVGGraphics2D grx = new SVGGraphics2D(document);

            JRGraphics2DExporter exporter = new JRGraphics2DExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, grx);
            exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(0));
            exporter.exportReport();

            grx.stream(new FileWriter(new File("reporting.svg")), true);
        }
        catch(JRException e){}
        catch(IOException e){}
    }

    public void getPDF(){
        try{

            JasperExportManager.exportReportToPdfFile(print, "reporting.pdf");

        }
        catch( JRException e){
            System.err.println( "JRException " + e);
        }

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

    }

}
