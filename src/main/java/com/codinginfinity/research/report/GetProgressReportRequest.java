/**
*	@file GetProgressReportRequest.java
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An object that indicates GetProgressReportRequest requirements.
*	@section Description
* 	This object is what will be passed to the Reporting class when a report needs to be generated. The report will be generated from the requirements specified within each attribute.
*	The report requested deals specifically with the progress status of publications.
*/

package com.codinginfinity.research.report;
public class GetProgressReportRequest implements Request{
	Entity requestedEntity;
	PublicationType requestedPublicationType;
        
        public GetProgressReportRequest(Entity entity, PublicationType type) {
            super();
            this.requestedEntity = entity;
            this.requestedPublicationType = type;
        }
        
        public Entity getEntity() {
            return requestedEntity;
        }
        
        public void setEntity(Entity entity) {
            this.requestedEntity = entity;
        }
        
        public PublicationType getPublicationType() {
            return requestedPublicationType;
        }
        
        public void setPublicationType(PublicationType type) {
            this.requestedPublicationType = type;
        }

}