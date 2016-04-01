/**
 *	@file ReportingMock.java
 *       @class Reporting
 *	@author COS301 Reporting Alpha Team
 *	@version 1.0 alpha
 *	@brief A mock object that mocks the generated reports
 *	@section Description
 * 	This class will mock the two service contracts provided by Reporting interface.
 *
 */
package com.codinginfinity.research.report.defaultImpl;

import com.codinginfinity.research.report.GetAccreditationUnitReportRequest;
import com.codinginfinity.research.report.GetAccreditationUnitReportResponse;
import com.codinginfinity.research.report.GetProgressReportRequest;
import com.codinginfinity.research.report.GetProgressReportResponse;
import com.codinginfinity.research.report.InvalidRequestException;
import com.codinginfinity.research.report.Reporting;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportingMock implements Reporting{

    private GetAccreditationUnitReportResponse accreditationUnitResponse;
    /**
     *
     * @param getAccreditationUnitReportRequest
     * @return GetAccreditationUnitReportResponse
     */
    @Override
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest getAccreditationUnitReportRequest) throws InvalidRequestException {
        if(buildAccreditationUnitReportResponse())
            return accreditationUnitResponse;
        else
            throw new InvalidRequestException();    }

    /**
     * Builds the Accreditation Unit report from the specified template file
     * @return
     */
    public boolean buildAccreditationUnitReportResponse(){
        JasperPrint jasperPrint;
        try{
            InputStream input = new FileInputStream(new File("AccreditationUnitReportTemplate.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);
            Map parameters = new HashMap();
            parameters.put("DETAILS", "Progress report from DATE to DATE");

            //Connection conn = Database.getConnection();
            PublicationList pub = new PublicationList();
            ArrayList<Publication> dataList = pub.getPublicationList();
            JRBeanCollectionDataSource ColDataSource = new JRBeanCollectionDataSource(dataList);
            jasperPrint = JasperFillManager.fillReport(report, parameters, ColDataSource);

        }
        catch(FileNotFoundException e){

            return false;

        }
        catch( JRException e){
            return false;
        }

        accreditationUnitResponse = new GetAccreditationUnitReportResponse(jasperPrint);
        return true;
    }


    /**
     * Implementation of the getProgressReport service contract
     * @param request
     * @return GetProgressReportResponse
     * @throws InvalidRequestException
     */
    @Override
    public GetProgressReportResponse getProgressReport(GetProgressReportRequest request) throws InvalidRequestException{
        //Validate request
        //if(request.getEntity() == null  && request.getPublicationType == null)
        //throw new InvalidRequestException();

        if(buildProgressReport())
            return progressResponse;
        else
            throw new InvalidRequestException();
    }

    /**
     * Builds the progress report from the specified template file
     * @return
     */
    public boolean buildProgressReport(){
        JasperPrint jasperPrint;
        try{
            InputStream input = new FileInputStream(new File("ProgressReportTemplate.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);
            Map parameters = new HashMap();
            parameters.put("DETAILS", "Progress report from DATE to DATE");

            //Connection conn = Database.getConnection();
            PublicationList pub = new PublicationList();
            ArrayList<Publication> dataList = pub.getPublicationList();
            JRBeanCollectionDataSource ColDataSource = new JRBeanCollectionDataSource(dataList);
            jasperPrint = JasperFillManager.fillReport(report, parameters, ColDataSource);

        }catch(FileNotFoundException e){

            return false;

        }
        catch( JRException e){
            return false;
        }

        progressResponse = new GetProgressReportResponse(jasperPrint);
        return true;
    }

    /**
     * Sets the response object. Allows tester to change response object at will
     * @param res
     */
    public void setProgressReportRes(GetProgressReportResponse res){
        this.progressResponse = res;
    }

    private GetProgressReportResponse progressResponse;

    class PublicationList {
        public ArrayList<Publication> getPublicationList() {
            ArrayList<Publication> publicationList = new ArrayList<Publication>();

            publicationList.add(produce("Title 1", 100));
            publicationList.add(produce("Title 2", 80));
            publicationList.add(produce("Title 3", 20));
            publicationList.add(produce("Title 4", 30));

            return publicationList;
        }

        /**
         * This method returns a Publication object,with title and progress set in it.
         */
        private Publication produce(String title, int progress) {
            Publication publication = new Publication();
            publication.setTitle(title);
            publication.setProgress(progress);
            return publication;
        }
    }

    //@Entity(name = "PUBLICATION")
    class Publication /*implements Serializable*/ {
        /* @Id
     @Column(name = "PUB_ID", nullable = false)
     @GeneratedValue(strategy = GenerationType.AUTO)*/
        private int publicationID;

        //@Column(name = "DATE")
        private Date date;

        //        @Column(name = "TITLE")
        private String title;

        //      @Column(name = "PROGRESS")
        private int progress;


        public Date getDate() {
            return date;
        }

        public void setDate(Date d) {
            this.date = d;
        }

        public int getPublicationID() {
            return publicationID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }
}