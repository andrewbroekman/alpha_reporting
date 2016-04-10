package com.codinginfinity.research.publication;

import com.codinginfinity.research.people.Person;
import com.codinginfinity.research.people.ResearchGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity // Name of entity
public class Publication implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int publicationID;

	@Column(nullable = false)
	private String title;

	//@Column(name = "AUTHOR")
	//private com.codinginfinity.research.people.Person author;

	@ManyToMany(targetEntity = Person.class)
	private List<Person> authors = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "groupID")
	private ResearchGroup group;

	@Embedded
	private LifeCycleState lifeCycleState;

	@Embedded
	private PublicationType publicationType;

	// date will be used to store either the envisaged publication date, or the published date, depending on the life cycle state.
	@Column
	private Date envisagedPublicationDate;

	// Getters and setters for local attributes

	public int getPublicationID() {
		return publicationID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String t) {
		this.title = t;
	}

/*
	public com.codinginfinity.research.people.Person getAuthor() {
		return author;
	}

	public void setAuthor(com.codinginfinity.research.people.Person a) {
		this.author = a;
	}
	*/

	public List<Person> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Person> a) {
		this.authors = a;
	}
	
	public ResearchGroup getGroup() {
		return group;
	}

	public void setGroup(ResearchGroup g) {
		this.group = g;
	}

	public LifeCycleState getLifeCycleState() {
		return lifeCycleState;
	}

	public void setLifeCycleState(LifeCycleState l) {
		this.lifeCycleState = l;
	}

	public PublicationType getPublicationType() {
		return publicationType;
	}

	public void setPublicationType(PublicationType p) {
		this.publicationType = p;
	}

	public Date getEnvisagedPublicationDate() {
		return envisagedPublicationDate;
	}

	public void setEnvisagedPublicationDate(Date d) {
		this.envisagedPublicationDate = d;
	}
}
