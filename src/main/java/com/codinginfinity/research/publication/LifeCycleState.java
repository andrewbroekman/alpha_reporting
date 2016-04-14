package com.codinginfinity.research.publication;

import java.io.Serializable;
import javax.persistence.*;

//Embedded class used in com.codinginfinity.research.publication.Publication
@Embeddable
public class LifeCycleState implements Serializable {

	@Column(nullable = false)
	private String lifeCycleState;

	@Column
	private String misc;

	public String getLifeCycleState() {
		return lifeCycleState;
	}

	public void setLifeCycleState(String l) {
		this.lifeCycleState = l;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String m) {
		this.misc = m;
	}
}