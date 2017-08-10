package com.syf.hibernate.model.xml;

import java.io.Serializable;

public class SchoolPK implements Serializable{
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
		if (o instanceof SchoolPK) {
			SchoolPK pk = (SchoolPK) o;
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
