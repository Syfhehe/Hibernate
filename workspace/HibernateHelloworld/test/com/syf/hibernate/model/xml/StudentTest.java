package com.syf.hibernate.model.xml;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.syf.hibernate.model.xml.Student;

public class StudentTest {

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
		Student stu = new Student();
		stu.setName("Syf");
		stu.setAge(12);
		
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(stu);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void main(String[] args){
		beforeClass();
	}

}
