package com.syf.hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class StudentTest {

	@Test
	public void test() {
		Student s = new Student();
		s.setId(1);
		s.setName("Syf");
		s.setAge(12);
		
		Configuration cfg = new Configuration();
		SessionFactory sf  = cfg.configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(s);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

}
