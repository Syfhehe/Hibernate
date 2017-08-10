package com.syf.hibernate.model.xml;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.syf.hibernate.model.xml.School;
import com.syf.hibernate.model.xml.SchoolPK;

public class SchoolTest {

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
		School s = new School();
		SchoolPK pk = new SchoolPK(); 
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
