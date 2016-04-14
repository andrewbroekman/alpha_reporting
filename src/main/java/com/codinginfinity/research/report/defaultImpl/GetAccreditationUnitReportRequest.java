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
import com.codinginfinity.research.people.ReqEntity;
import com.codinginfinity.research.report.Request;

public class GetAccreditationUnitReportRequest implements Request
{
	private static final long serialVersionUID = 1L;
	ReqEntity requestedEntity;
	LifeCycleState requestedLifeCycleState;
	PublicationType requestedPublicationType;
	Period requestedPeriod;
        public GetAccreditationUnitReportRequest(){
            super();
            this.requestedEntity = null;
            this.requestedPublicationType = null;
            this.requestedLifeCycleState=null;
            this.requestedPeriod=null;
        }
        
	public GetAccreditationUnitReportRequest(ReqEntity entity, LifeCycleState state,PublicationType type, Period
			period)
	{
		super();
		this.requestedEntity = entity;
		this.requestedPublicationType = type;
		this.requestedLifeCycleState=state;
		this.requestedPeriod=period;
	}


        /**
         * Function for the return of the entity for the accreditation request object
         * @return 
         */
	public ReqEntity getEntity() {
		return requestedEntity;
	}
        /**
         * Function for setting of the entity for the accreditation request object
         * @param entity 
         */
	public void setEntity(ReqEntity entity) {
		this.requestedEntity = entity;
	}
        /**
         * Function to return the lifeCycleState of the accreditation request object
         * @return 
         */
	public LifeCycleState getLifeCycleState(){
		return requestedLifeCycleState;
	}
        /**
         * Function for setting the life cycle state of the accreditation request object 
         * @param state 
         */
	public void setLifeCycleState(LifeCycleState state) {
		this.requestedLifeCycleState=state;
	}
        /**
         * Function for return the publication type for the accreditation request object
         * @return 
         */
	public PublicationType getPublicationType() {
		return requestedPublicationType;
	}
        /**
         * Function for setting the publication type for the accreditation request object
         * @param type 
         */
	public void setPublicationType(PublicationType type) {
		this.requestedPublicationType = type;
	}
        /**
         * Function for returning the period of the accreditation request object
         * @return 
         */
	public Period getPeriod(){
		return this.requestedPeriod;
	}
        /**
         * Function for setting the period of the accreditation request object 
         * @param period 
         */
	public void setPeriod(Period period){
		this.requestedPeriod=period;
	}
}
