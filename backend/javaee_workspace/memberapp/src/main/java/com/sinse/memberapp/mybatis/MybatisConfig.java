package com.sinse.memberapp.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//mybatis-config.xml을 읽어들여, 개발시 쿼리문을 수행할 수 있는 객체인 SqlSession을 
//SqlSessionFactory로 부터 얻게 해주는 유틸 클래스.
//mybatis는 내부적으로 JDBC를 사용함. 하지만 개발자가 상투적인 JDBC코드를 일일이 작성하지 않게 하기 위함이 목적.
//따라서 코드들을 숨겨놓고 그 대신 SqlSession을 통해 쿼리를 수행할 수 있게 해줌.
public class MybatisConfig {
	private static MybatisConfig instance;
	private SqlSessionFactory sqlSessionFactory;
	
	
	public MybatisConfig() {
		String resource = "com/sinse/memberapp/mybatis/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MybatisConfig getInstance() {
		if(instance == null) {
			instance = new MybatisConfig();
		}
		
		return instance;
	}
	
	//쿼리 실행 객체인 sqlSession 반환
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
}
