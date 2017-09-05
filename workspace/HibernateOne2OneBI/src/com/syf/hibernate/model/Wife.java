package com.syf.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Wife {
	private int id;
	private String name;
	private Husband huaband;

	@OneToOne(mappedBy = "wife")
	public Husband getHuaband() {
		return huaband;
	}

	public void setHuaband(Husband huaband) {
		this.huaband = huaband;
	}

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
