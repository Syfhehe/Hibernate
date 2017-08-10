package com.syf.hibernate.model.annotation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.syf.hibernate.model.annotation.SchoolAnt;
import com.syf.hibernate.model.annotation.SchoolAntPK;

public class SchoolAntTest {

	private static SessionFactory sf = null;
	
	@BeforeClass
	public static void beforeClass(){
		Configuration cfg = new Configuration();
		sf  = cfg.configure().buildSessionFactory();
	}
	
	@AfterClass
	public static void afterClass(){
		sf.close();
	}
	
	@Test
	public void test() {
		SchoolAnt s = new SchoolAnt();
		SchoolAntPK pk = new SchoolAntPK(); 
		pk.setId(1);
		pk.setName("syf");
		s.setPk(pk);
		s.setAddress("Pujiang");
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(s);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void main(String[] args){
		beforeClass();
	}

}
