package com.syf.hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.syf.hibernate.model.Husband;
import com.syf.hibernate.model.Wife;

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
		
		
		Session session1 = sf.getCurrentSession();
		session1.beginTransaction();
		Husband h1 = session1.get(Husband.class, 1);
		System.out.println(h1.getWife().getName());
		Wife w1  = session1.get(Wife.class, 1);
		System.out.println(w1.getHuaband().getName());

		session1.getTransaction().commit();	

	}

	public static void main(String[] args) {
		beforeClass();
	}

}
