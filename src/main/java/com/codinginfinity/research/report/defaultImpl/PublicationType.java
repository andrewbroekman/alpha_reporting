package com.codinginfinity.research.report.defaultImpl;


public class PublicationType {
	
	private String name;

	private double accreditationPoints;

        public PublicationType(String name, double accreditationPoints) {
            this.name = name;
            this.accreditationPoints = accreditationPoints;
        }

    PublicationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        

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