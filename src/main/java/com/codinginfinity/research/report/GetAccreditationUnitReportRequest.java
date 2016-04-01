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
public class GetAccreditationUnitReportRequest {
	Entity requestedEntity;
	LifeCycleState requestedLifeCycleState;
	PublicationType requestedPublicationType;
	Period requestedPeriod;
}