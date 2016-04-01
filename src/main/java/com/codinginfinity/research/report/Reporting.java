/**
*	@file Reporting.java
*   @interface Reporting
*	@author COS301 Reporting Alpha Team
*	@version 1.0 alpha*
*	@brief An interface that defines two service contracts*
*	@section Description
* 	This interface provides two service contracts that need to be implemented of opening a file, reading individual indexes of the buffer and closing the file again.
*
*/

package com.codinginfinity.research.report;
public interface Reporting {
    
    /**
     * Service Contract Contract that allows a user to request a report on the accreditation units of Publications
     * @param getAccreditationUnitReportRequest An object that will specify exactly what has been requested
     * @return GetAccreditationUnitReportResponse that will contain the information requested
     * @throws InvalidRequestException
     */
    public GetAccreditationUnitReportResponse getAccreditationUnitReport(GetAccreditationUnitReportRequest getAccreditationUnitReportRequest) throws  InvalidRequestException;//,InvalidEndDateException;
    
    /**
     * Service Contract that allows a user to request a report on the progress of Publications
     * @param getProgressReportRequest An object that will specify exactly what has been requested
     * @return GetProgressReportResponse that will contain the information requested
     * @throws InvalidRequestException
     */
    public GetProgressReportResponse getProgressReport(GetProgressReportRequest getProgressReportRequest) throws InvalidRequestException;
    
}
