package com.codinginfinity.research.report.defaultImpl;

import java.util.Date;

//Mock object for the Period object used in com.codinginfinity.research.publication
public class Period{

	public Period(Date startD,Date endD)
	{
		startDate=startD;
		endDate=endD;
	}

	// startDate and endDate represent the lower and upper limit of the Period object, respectively
	private Date startDate;

	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date s) {
		this.startDate = s;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date e) {
		this.endDate = e;
	}
}