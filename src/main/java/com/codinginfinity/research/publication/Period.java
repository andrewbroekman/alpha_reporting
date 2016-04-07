package com.codinginfinity.research.publication;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

//Mock object for the Period object used in com.codinginfinity.research.publication
public class Period implements Serializable {

	// startDate and endDate represent the lower and upper limit of the Period object, respectively

	@Column(nullable = false)
	private Date startDate;

	@Column(nullable = false)
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