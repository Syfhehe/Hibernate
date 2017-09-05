package com.syf.hibernate.model;

import java.util.ArrayList;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class OneToManyTest {

	private static SessionFactory sf = null;

	@BeforeClass
	public static void beforeClass() {
		Configuration cfg = new Configuration();
		sf = cfg.configure().buildSessionFactory();
	}

	@AfterClass
	public static void afterClass() {
		sf.close();
	}

	@Test
	public void test() {
		Student s = new Student();
		s.setName("Student");
		
		Course g = new Course();
		g.setName("Course");
		
		s.getCourses().add(g);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(g);
		session.save(s);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		beforeClass();
	}

}
