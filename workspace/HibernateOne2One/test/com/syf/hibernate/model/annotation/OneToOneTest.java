package com.syf.hibernate.model.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class OneToOneTest {

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
		Husband h = new Husband();
		h.setName("husband");
		
		Wife w = new Wife();
		w.setName("wife");
		
		h.setWife(w);
		w.setHuaband(h);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(w);
		session.save(h);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		beforeClass();
	}

}
