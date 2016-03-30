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

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GetAccreditationUnitReportResponse {



    public GetAccreditationUnitReportResponse(Document svg/*, PDF pdf, XML xml*/) throws JRException, IOException{
        super();
        this.svg = svg;

        InputStream inputStream=new FileInputStream((new File("src/main/java/com/codinginfinity/research/report/AccreditationUnitReport.jrxml")));
        JasperDesign jasperDesign= JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,String> map = new HashMap<String,String>();
        map.put("jasper report","Accreditation Unit");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null);

        OutputStream outputStream=new FileOutputStream(new File("src/main/java/com/codinginfinity/research/report/report.pdf"));
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
        System.out.println("done");

        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        svg = domImpl.createDocument(null, "svg", null);
        SVGGraphics2D grx = new SVGGraphics2D(svg);

        JRGraphics2DExporter exporter = new JRGraphics2DExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, grx);
        exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(0));
        exporter.exportReport();

        grx.stream(new FileWriter(new File("src/main/java/com/codinginfinity/research/report/AccreditationUnitReport.svg")), true);

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
