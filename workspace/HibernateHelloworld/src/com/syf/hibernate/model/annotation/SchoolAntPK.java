package com.syf.hibernate.model.annotation;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SchoolAntPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof SchoolAntPK) {
			SchoolAntPK pk = (SchoolAntPK) o;
			if (this.id == pk.getId() && this.name.equals(pk.getName()))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

}
