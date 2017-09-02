package com.syf.hibernate.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
		Session session = sf.getCurrentSession();
		session.beginTransaction();

		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setName("c" + i);
			session.save(c);
		}

		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setId(1);
			Topic t = new Topic();
			t.setCategory(c);
			t.setTitle("t" + i);
			t.setCreateDate(new Date());
			session.save(t);
		}

		for (int i = 0; i < 10; i++) {
			Topic t = new Topic();
			t.setId(1);
			Msg m = new Msg();
			m.setCont("m" + i);
			m.setTopic(t);
			session.save(m);

		}

		session.getTransaction().commit();
	}
	
	@Test
	public void testHQL_1() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.msgs is empty");
		for(Object o: q.list()) {
			Topic t = (Topic) o;
			System.out.println(t.getId()+"-"+t.getTitle());
		}
		session.getTransaction().commit();
	}
	
	@Test
	public void testHQL_2() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.title like '%5'");
		for(Object o: q.list()) {
			Topic t = (Topic) o;
			System.out.println(t.getId()+"-"+t.getTitle());
		}
		session.getTransaction().commit();
	}
	
	@Test
	public void testHQL_3() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where not exists (select m.id from Msg m where m.topic.id = t.id)");
		for(Object o: q.list()) {
			Topic t = (Topic) o;
			System.out.println(t.getId()+"-"+t.getTitle());
		}
		session.getTransaction().commit();
	}
	
	@Test
	public void testHQL_4() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		SQLQuery q = session.createSQLQuery("select * from category limit 2 , 4").addEntity(Category.class);
		for(Object o: q.list()) {
			Category t = (Category) o;
			System.out.println(t.getId()+"-"+t.getName());
		}
		session.getTransaction().commit();
	}
	
	

}
