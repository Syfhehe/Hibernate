package com.syf.hibernate.model;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class QBCTest {

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
	public void testQBC_1() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Criteria c = session.createCriteria(Topic.class)
				.add(Restrictions.gt("id", 2))
				.add(Restrictions.lt("id", 8))
				.add(Restrictions.like("title", "t_"))
				.createCriteria("category")
				.add(Restrictions.between("id", 3, 5));
		for(Object o: c.list()) {
			Topic t = (Topic) o;
			System.out.println(t.getId()+"-"+t.getTitle());
		}
		session.getTransaction().commit();
	}
	
	
	
	

}
