package com.syf.hibernate.model.xml;

public class School {
	private SchoolPK pk;
	private String address;

	public SchoolPK getPk() {
		return pk;
	}

	public void setPk(SchoolPK pk) {
		this.pk = pk;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
