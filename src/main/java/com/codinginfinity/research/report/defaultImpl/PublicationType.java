package com.codinginfinity.research.report.defaultImpl;


public class PublicationType {
	
	private String name;

	private double accreditationPoints;
        /**
         * Constructor for the instantiation of the publication name and the accreditationPoints
         * @param name
         * @param accreditationPoints 
         */
        public PublicationType(String name, double accreditationPoints) {
            this.name = name;
            this.accreditationPoints = accreditationPoints;
        }
    
    PublicationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
        /**
         * Function to return the name of the Publications
         * @return 
         */
	public String getName() {
		return name;
	}
        /**
         * Function to set the name of the Publication object
         * @param t 
         */
	public void setName(String t) {
		this.name = t;
	}
        /**
         * Function for returning the accreditationPoints of the Publication object
         * @return 
         */
	public double getAccreditationPoints() {
		return accreditationPoints;
	}
        /**
         * Function for setting the accreditationPoints for the Publication object
         * @param a 
         */
	public void setAccreditationPoints(double a) {
		this.accreditationPoints = a;
	}
}