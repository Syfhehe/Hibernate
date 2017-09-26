package com.syf.hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class ManyToManyTest {

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
		
		Course c1 = new Course();
		c1.setName("Course");
		
		
		s.getCourses().add(c1);
		c1.getStudents().add(s);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(c1);
		session.save(s);
		session.getTransaction().commit();	
		
		Session session1 = sf.getCurrentSession();
		session1.beginTransaction();		
		System.out.println(session1.get(Student.class,1).getCourses().get(0).getName());
		System.out.println(session1.get(Course.class,1).getStudents().get(0).getName());

		session1.getTransaction().commit();	
	}

	public static void main(String[] args) {
		beforeClass();
	}

}
