package com.syf.hibernate.model.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.syf.hibernate.model.Group;
import com.syf.hibernate.model.User;

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
		Group g = new Group();
		User u =new User();
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
//		session.save(g);
//		session.save(u);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		beforeClass();
	}

}
