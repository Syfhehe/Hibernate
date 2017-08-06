package com.syf.hibernate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.syf.hibernate.Session;

public class StudentsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void student() throws Exception {
		Student s = new Student();
		s.setId(2);
		s.setName("syf");
		s.setAge(12);

		Session session = new Session();
		session.save(s);
	}

	@Test
	public void jdbc() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/syf?useSSL=false", "root", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
