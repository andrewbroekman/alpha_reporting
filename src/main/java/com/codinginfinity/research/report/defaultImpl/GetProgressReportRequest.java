/**
*	@file GetProgressReportRequest.java
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An object that indicates GetProgressReportRequest requirements.
*	@section Description
* 	This object is what will be passed to the Reporting class when a report needs to be generated. The report will be generated from the requirements specified within each attribute.
*	The report requested deals specifically with the progress status of publications.
*/

package com.codinginfinity.research.report.defaultImpl;

import com.codinginfinity.research.report.Request;
import com.codinginfinity.research.people.ReqEntity;

public class GetProgressReportRequest implements Request
{
    private static final long serialVersionUID = 1L;
	ReqEntity requestedEntity;
	PublicationType requestedPublicationType;
        
        /**
         * This is a constructor for the GetProgressReportRequest class.
         */
        public GetProgressReportRequest(){
            super();
            this.requestedEntity = null;
            this.requestedPublicationType = null;
        }
        
        /**
         * This is a constructor with a entity parameter, it is required when making a request with just an entity.
         * @param entity 
         */
        public GetProgressReportRequest(ReqEntity entity){
            super();
            this.requestedEntity = entity;
            this.requestedPublicationType = null;
        }
        
        /**
         * This is a constructor with a Publication parameter, it is required when making a request with only Publication type.
         * @param type 
         */
        public GetProgressReportRequest(PublicationType type){
            super();
            this.requestedEntity = null;
            this.requestedPublicationType = type;
        }
        
        /**
         * This is a constructor with Request Entity and PublicationType parameters, it required when creating a GetProgressReportRequest
         * object with the Request Entity and PublicationType parameters
         * @param entity
         * @param type 
         */
        public GetProgressReportRequest(ReqEntity entity, PublicationType type) {
            super();
            this.requestedEntity = entity;
            this.requestedPublicationType = type;
        }
        
        /**
         * This is a get function that returns a requestedEntity object
         * @return ReqEntity
         */
        public ReqEntity getEntity() {
            return requestedEntity;
        }
        
        /**
         * This setter function for assigning an entity to the requestedEntity.
         * @param entity 
         */
        public void setEntity(ReqEntity entity) {
            this.requestedEntity = entity;
        }
        
        /**
         * This is a getter function for getting a PublicationType object.
         * @return PublicationType
         */
        public PublicationType getPublicationType() {
            return requestedPublicationType;
        }
        
        /**
         * This is a setter function for setting a PublicationType to the requestedPublicationType request.
         * @param null
         */
        public void setPublicationType(PublicationType type) {
            this.requestedPublicationType = type;
        }

}