package com.syf.hibernate.model.annotation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ant_school")
public class SchoolAnt {
	@Id
	private SchoolAntPK pk;
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public SchoolAntPK getPk() {
		return pk;
	}

	public void setPk(SchoolAntPK pk) {
		this.pk = pk;
	}

}
