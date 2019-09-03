package com.my.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private SqlSessionFactory SqlSessionFactory;
	private InputStream inputStream;
	
	public SqlSessionFactory getSqlSessionFactory() {
		String resource = "com/my/db/mybatis-config.xml";
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SqlSessionFactory;
	}
	
}
