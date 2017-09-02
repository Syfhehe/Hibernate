package com.syf.hibernate.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
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
	public void testHQL_01() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category");
		List<Category> cs = (List<Category>) q.list();
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	@Test
	public void testHQL_02() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c where c.name >'c5'");
		List<Category> cs = (List<Category>) q.list();
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	@Test
	public void testHQL_03() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c order by c.name desc");
		List<Category> cs = (List<Category>) q.list();
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	// distinct不会对象重复
	@Test
	public void testHQL_04() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("select distinct c from Category c order by c.name desc");
		List<Category> cs = (List<Category>) q.list();
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	@Test
	public void testHQL_05() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c where c.id > :min and c.id < :max");
		q.setParameter("min", 6);
		q.setParameter("max", 9);
		List<Category> cs = (List<Category>) q.list();
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	@Test
	public void testHQL_06() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		// Query q = session.createQuery("from Category c where c.id > :min and c.id <
		// :max");
		// q.setParameter("min", 6);
		// q.setParameter("max", 9);

		// 链式编程
		Query q = session.createQuery("from Category c where c.id > :min and c.id < :max").setInteger("min", 6)
				.setInteger("max", 9);
		List<Category> cs = (List<Category>) q.list();
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	@Test
	public void testHQL_07() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c order by c.name desc");
		q.setMaxResults(4);// 每页4条
		q.setFirstResult(2);// 从第二条开始
		List<Category> cs = (List<Category>) q.list();
		for (Category c : cs) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
	}

	@Test
	public void testHQL_08() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("select c.id, c.name from Category c order by c.name desc");
		List<Object[]> cs = (List<Object[]>) q.list();
		for (Object[] c : cs) {
			System.out.println(c[0] + "-" + c[1]);
		}
		session.getTransaction().commit();
	}

	// 有两个select语句 原因是fetch type是lazy
	@Test
	public void testHQL_09() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.category.id = 1");
		List<Topic> ts = (List<Topic>) q.list();
		for (Topic t : ts) {
			System.out.println(t.getTitle());
			System.out.println(t.getCategory().getName());
		}
		session.getTransaction().commit();
	}

	// 板块1中所有的message
	@Test
	public void testHQL_10() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Msg m where m.topic.category.id = 1");
		for (Object o : q.list()) {
			Msg m = (Msg) o;
			System.out.println(m.getCont());
		}
		session.getTransaction().commit();
	}

	// VO Value Object
	@Test
	public void testHQL_11() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery(
				"select new com.syf.hibernate.model.MsgInfo(m.id, m.cont, m.topic.title, m.topic.category.name) from Msg m");

		for (Object o : q.list()) {
			MsgInfo m = (MsgInfo) o;
			System.out.println(m.getCont());
		}
		session.getTransaction().commit();
	}

	@Test
	public void testHQL_12() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("select t.title, c.name from Topic t join t.category c");//t.category 变量上的导航条件

		for (Object o : q.list()) {
			Object[] m = (Object[]) o;
			System.out.println(m[0] + "-" + m[1]);
		}
		session.getTransaction().commit();
	}
	
	@Test
	public void testHQL_13() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Msg m where m = :MsgToSearch");
		Msg m = new Msg();
		m.setId(1);
		q.setParameter("MsgToSearch", m);
		Msg mResult = (Msg)q.uniqueResult();
		System.out.println(mResult.getCont());
		session.getTransaction().commit();
	}

}
