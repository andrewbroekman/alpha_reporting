/**
*	@file GetAccreditationUnitReportRequest.java
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An object that indicates GetAccreditationUnitReportRequest requirements.
*	@section Description
* 	This object is what will be passed to the Reporting class when a report needs to be generated. The report will be generated from the requirements specified within each attribute.
*	The report requested deals specifically with the accreditation units of people, groups, or publications.
*/

package com.codinginfinity.research.report;

//import java.io.File;

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

public class GetAccreditationUnitReportRequest {
	Entity requestedEntity;
	LifeCycleState requestedLifeCycleState;
	PublicationType requestedPublicationType;
	Period requestedPeriod;

	public GetAccreditationUnitReportRequest(Entity entity, LifeCycleState state,PublicationType type, Period period) {
		super();
		this.requestedEntity = entity;
		this.requestedPublicationType = type;
		this.requestedLifeCycleState=state;
		this.requestedPeriod=period;
	}

	public Entity getEntity() {
		return requestedEntity;
	}

	public void setEntity(Entity entity) {
		this.requestedEntity = entity;
	}

	public LifeCycleState getLifeCycleState(){
		return requestedLifeCycleState;
	}

	public void setLifeCycleState(LifeCycleState state) {
		this.requestedLifeCycleState=state;
	}

	public PublicationType getPublicationType() {
		return requestedPublicationType;
	}

	public void setPublicationType(PublicationType type) {
		this.requestedPublicationType = type;
	}

	public Period getPeriod(){
		return this.requestedPeriod;
	}

	public void setPeriod(Period period){
		this.requestedPeriod=period;
	}

}