/**
*	@file ReportingRequest.java
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An object that indicates requested report requirements.
*	@section Description
* 	This object is what will be passed to the Reporting class when a report needs to be generated. The report will be generated from the requirements specified within each attribute.
*
*/

package com.codinginfinity.research.report;
public class ReportingRequest {
	Person entity;
	Publication publication;
	Period period;
}