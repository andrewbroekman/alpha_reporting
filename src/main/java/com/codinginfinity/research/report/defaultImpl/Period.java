package com.codinginfinity.research.report.defaultImpl;

import java.util.Date;

//Mock object for the Period object used in com.codinginfinity.research.publication
public class Period{
        
    /**
     * This is a constructor with start and end dates.
     * @param startD
     * @param endD 
     */
    public Period(Date startD,Date endD)
    {
            startDate=startD;
            endDate=endD;
    }

    // startDate and endDate represent the lower and upper limit of the Period object, respectively
    private Date startDate;

    private Date endDate;
    
    /**
     * This is a getter function for getting startDate object.
     * @return Date
     */
    public Date getStartDate() {
            return startDate;
    }
    
    /**
     * This a setter function for setting a startDate object.
     * @param s 
     */
    public void setStartDate(Date s) {
            this.startDate = s;
    }
    
    /**
     * This is a getter function for getting the end date.
     * @return Date
     */
    public Date getEndDate() {
            return endDate;
    }
    /**
     * This is a setter function for setting the endDate object.
     * @param e 
     */
    public void setEndDate(Date e) {
            this.endDate = e;
    }
}