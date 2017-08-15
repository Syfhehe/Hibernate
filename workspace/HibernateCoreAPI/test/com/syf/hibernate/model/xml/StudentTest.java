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
		//Transient
		Student stu = new Student();
		stu.setName("Syf");
		stu.setAge(12);

		// openSession是永远打开新的session
		// Session session = sf.openSession();
		
		// getCurrentSession,如果已经存在，就用原来的session, 不用close
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		//Persistent
		session.save(stu);
		session.getTransaction().commit();
		
		//Detached
		stu.getAge();
	}
	
	@Test
	public void testGet() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Student stu = session.get(Student.class, 1);
		session.getTransaction().commit();
		System.out.println("name: "+ stu.getName());		
	}
	
	@Test
	public void testLoad() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Student stu = session.load(Student.class, 1);
		session.getTransaction().commit();
		//load生成的是代理对象 有id 但是name的属性为空
		System.out.println("id: "+ stu.getId());	
		//System.out.println("name: "+ stu.getName());		
	}

	//Detached变成Persistence状态
	@Test
	public void testUpdate1() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Student stu = session.get(Student.class, 1);
		session.getTransaction().commit();
		
		stu.setName("ShenYifan");
		
		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		session2.update(stu);
		session2.getTransaction().commit();		
	}
	
	//Transient一定要设置好ID 并且数据库里存在才可以 不然会报错
	@Test
	public void testUpdate2() {
		Student stu = new Student();
		stu.setId(1);
		
		stu.setName("ShenYifan");
		
		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		session2.update(stu);
		session2.getTransaction().commit();		
	}
	
	//Persistence更新所有字段
	@Test
	public void testUpdate3() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Student stu = session.get(Student.class, 1);
		stu.setName("ShenYifan");
		session.getTransaction().commit();
	}
	
	//Persistence更新想更新的字段 在XML中设置dynamic-update
	@Test
	public void testUpdate4() {
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Student stu = session.get(Student.class, 1);
		stu.setName("ShenYifan1");
		session.getTransaction().commit();
	}
	
	
	public static void main(String[] args) {
		beforeClass();
	}

}
