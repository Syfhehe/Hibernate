package com.syf.hibernate.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MsgInfo {
	
	public MsgInfo(int id, String cont, String topicName, String categoryName) {
		super();
		this.id = id;
		this.cont = cont;
		this.topicName = topicName;
		this.categoryName = categoryName;
	}

	private int id;
	private String cont;
	private String topicName;
	private String categoryName;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
