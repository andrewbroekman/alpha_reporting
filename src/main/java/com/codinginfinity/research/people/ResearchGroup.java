package com.codinginfinity.research.people;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class ResearchGroup implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long groupID;

	@Column(length = 50)
	private String name;

//	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
//	@Column(nullable = false)
//	private List<Person> person = new ArrayList<>();

	public long getGroupID() {
		return groupID;
	}

	public String getName() {
		return name;
	}

	public void setName(String g) {
		this.name = g;
	}

//	public List<Person> getPerson() {
//		return person;
//	}

//	public void setPerson(List<Person> p) {
//		this.person = p;
//	}
}