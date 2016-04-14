package com.codinginfinity.research.report.defaultImpl;

//Embedded class used in com.codinginfinity.research.publication.Publication
public class LifeCycleState{

	private String lifeCycleState;

	private String misc;

	public LifeCycleState(String lc, String m)
	{
		lifeCycleState=lc;
		misc=m;
	}

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