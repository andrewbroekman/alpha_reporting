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
     * @return Object that will contain the information requested
     * @todo Define the type of Object that will be received and returned
     */
    public Object getAccreditationUnitReport(Object getAccreditationUnitReportRequest);
    
    /**
     * Service Contract that allows a user to request a report on the progress of Publications
     * @param getProgressReportRequest An object that will specify exactly what has been requested
     * @return Object that will contain the information requested
     * @todo Define the type of Object that will be received and returned
     */
    public Object getProgressReport(Object getProgressReportRequest);
    
}
