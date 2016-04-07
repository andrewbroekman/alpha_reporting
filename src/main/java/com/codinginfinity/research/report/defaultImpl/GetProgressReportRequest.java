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
import com.codinginfinity.research.publication.*;
import com.codinginfinity.research.people.ReqEntity;

public class GetProgressReportRequest implements Request
{
    private static final long serialVersionUID = 1L;
	ReqEntity requestedEntity;
	PublicationType requestedPublicationType;
        
        public GetProgressReportRequest(ReqEntity entity, PublicationType type) {
            super();
            this.requestedEntity = entity;
            this.requestedPublicationType = type;
        }
        
        public ReqEntity getEntity() {
            return requestedEntity;
        }
        
        public void setEntity(ReqEntity entity) {
            this.requestedEntity = entity;
        }
        
        public PublicationType getPublicationType() {
            return requestedPublicationType;
        }
        
        public void setPublicationType(PublicationType type) {
            this.requestedPublicationType = type;
        }

}