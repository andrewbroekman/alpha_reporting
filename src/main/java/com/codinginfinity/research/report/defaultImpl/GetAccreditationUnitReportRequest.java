/**
*	@file GetAccreditationUnitReportRequest.java
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An object that indicates GetAccreditationUnitReportRequest requirements.
*	@section Description
* 	This object is what will be passed to the Reporting class when a report needs to be generated. The report will be generated from the requirements specified within each attribute.
*	The report requested deals specifically with the accreditation units of people, groups, or publications.
*/

package com.codinginfinity.research.report.defaultImpl;



import com.codinginfinity.research.publication.*;
import com.codinginfinity.research.people.Entity;
import com.codinginfinity.research.report.Request;

public class GetAccreditationUnitReportRequest implements Request
{
	private static final long serialVersionUID = 1L;
	Entity requestedEntity;
	LifeCycleState requestedLifeCycleState;
	PublicationType requestedPublicationType;
	Period requestedPeriod;
	public GetAccreditationUnitReportRequest(Entity entity, LifeCycleState state,PublicationType type, Period
			period)
	{
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