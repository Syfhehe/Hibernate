package com.syf.hibernate.model.annotation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ant_teacher")
@SequenceGenerator(name = "teacherSEQ", sequenceName = "teacherSEQ_DB")
public class Teacher {
	private int id;
	private String name;
	private String title;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacherSEQ")
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
