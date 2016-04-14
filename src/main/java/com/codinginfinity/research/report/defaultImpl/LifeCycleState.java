package com.codinginfinity.research.report.defaultImpl;

//Embedded class used in com.codinginfinity.research.publication.Publication
public class LifeCycleState{

	private String lifeCycleState;

	private String misc;
        
        /**
         * Constructor to initialize the lifeCycleState and misc at construction
         * @param lc
         * @param m 
         */
	public LifeCycleState(String lc, String m)
	{
		lifeCycleState=lc;
		misc=m;
	}
        /**
         * Function to return the lifeCycleState of LifeCycleState object 
         * @return 
         */
	public String getLifeCycleState() {
		return lifeCycleState;
	}
        /**
         * Function to setting the LifeCycleState of the LifeCycleState object
         * @param l 
         */
	public void setLifeCycleState(String l) {
		this.lifeCycleState = l;
	}
        /**
         * Function for returning the misc, this just indicates the progress of the team
         * @return 
         */
	public String getMisc() {
		return misc;
	}
        /**
         * Function for setting the misc variable
         * @param m 
         */
	public void setMisc(String m) {
		this.misc = m;
	}
}