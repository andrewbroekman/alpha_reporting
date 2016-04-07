package com.codinginfinity.research.people;

import com.codinginfinity.research.publication.Publication;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Person implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long personID;

	@Column(nullable = false, length = 50)
	private String firstNames;

	@Column(nullable = false, length = 25)
	private String surname;

	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "groupID")
	private ResearchGroup group;

	@Column(nullable = false)
	private String researchGroupAssociationType;

	@Column
	private Date startDate;

	@Column
	private Date endDate;

	@ManyToMany(targetEntity = Publication.class)
//	@JoinTable(joinColumns = {@JoinColumn(referencedColumnName = "personID")}, inverseJoinColumns = {@JoinColumn(referencedColumnName = "publicationID")})
	public List<Publication> publications = new ArrayList<>();

//	@Column
//	@ManyToMany(targetEntity = com.codinginfinity.research.publication.Publication.class)
//	private Set publications;

	@Column(nullable = false, length = 30)
	private String primaryEmail;

	@Column(nullable = false, length = 100)
	private String organisation;

	public long getPersonID() {
		return personID;
	}

	public String getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(String n) {
		this.firstNames = n;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String s) {
		this.surname = s;
	}

	public ResearchGroup getGroup() {
		return group;
	}

	public void setGroup(ResearchGroup g) {
		this.group = g;
	}

	public String getResearchGroupAssociationType() {
		return researchGroupAssociationType;
	}

	public void setResearchGroupAssociationType(String g) {
		this.researchGroupAssociationType = g;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date as) {
		this.startDate = as;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date ae) {
		this.endDate = ae;
	}

	public List<Publication> getPublication() {
		return publications;
	}

	public void setPublications(List<Publication> p) {
		this.publications = p;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String e) {
		this.primaryEmail = e;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String o) {
		this.organisation = o;
	}

}