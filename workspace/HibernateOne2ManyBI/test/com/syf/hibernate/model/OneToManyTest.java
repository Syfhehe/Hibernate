package com.syf.hibernate.model;

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
		
		g.setStudent(s);
		s.getCourses().add(g);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(g);
		session.save(s);
		session.getTransaction().commit();	
		
		Session session1 = sf.getCurrentSession();
		session1.beginTransaction();		
		System.out.println(session1.get(Student.class,1).getCourses().get(0).getName());
		System.out.println(session1.get(Course.class,1).getStudent().getName());
		session1.getTransaction().commit();	
	}

	public static void main(String[] args) {
		beforeClass();
	}

}
