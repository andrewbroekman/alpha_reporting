package com.codinginfinity.research.publication;

import java.io.Serializable;
import javax.persistence.*;

//Embedded class used in com.codinginfinity.research.publication.Publication.
@Embeddable
public class PublicationType implements Serializable {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private double accreditationPoints;

	public String getName() {
		return name;
	}

	public void setName(String t) {
		this.name = t;
	}

	public double getAccreditationPoints() {
		return accreditationPoints;
	}

	public void setAccreditationPoints(double a) {
		this.accreditationPoints = a;
	}
}