package com.syf.hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class ManyToOneTest {

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
		Course c = new Course();
		c.setName("Course");
		Student u =new Student();
		u.setName("Student");
		
		c.setStudent(u);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(c);
		session.save(u);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		beforeClass();
	}

}
