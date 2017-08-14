package com.syf.hibernate;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import com.syf.hibernate.model.Student;

public class Session {

	String tableName = "_Student";
	Map<String, String> cfs = new HashMap<String, String>();
	String[] methodNames;

	public Session() {
		cfs.put("_id", "id");
		cfs.put("_name", "name");
		cfs.put("_age", "age");
		methodNames = new String[cfs.size()];
	}

	public void save(Student s) throws Exception {
		String sql = creatSQL();
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/syf?useSSL=false", "root", "123456");
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < methodNames.length; i++) {
			Method m = s.getClass().getMethod(methodNames[i]);
			Class<?> r = m.getReturnType();

			if (r.getName().equals("java.lang.String")) {
				String returnValue = (String) m.invoke(s);
				ps.setString(i + 1, returnValue);
			}
			
			if (r.getName().equals("int")) {
				Integer returnValue = (Integer) m.invoke(s);
				ps.setInt(i + 1, returnValue);
			}
			
			System.out.println(m.getName() + "|" + r);
		}
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	private String creatSQL() {
		String str1 = "";
		int index = 0;
		for (String s : cfs.keySet()) {
			String value = cfs.get(s);
			value = Character.toUpperCase(value.charAt(0)) + value.substring(1);
			methodNames[index] = "get" + value;
			str1 += s + ",";
			index++;
		}
		str1 = str1.substring(0, str1.length() - 1);
		System.out.println(str1);

		String str2 = "";
		for (int i = 0; i < cfs.size(); i++) {
			str2 += "?,";
		}
		str2 = str2.substring(0, str2.length() - 1);
		System.out.println(str2);

		String sql = "insert into " + tableName + "(" + str1 + ")" + " values (" + str2 + ")";
		System.out.println(sql);
		return sql;
	}

}
