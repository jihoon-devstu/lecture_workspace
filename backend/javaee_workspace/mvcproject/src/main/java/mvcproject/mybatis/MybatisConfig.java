package mvcproject.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * Mybatis FrameWork의 설정은 java 클래스가 아닌 xml 문서로 구성되어있다.
 * 하지만 xml은 프로그래밍 언어가 아니므로 , java 를 이용하여 xml을 해석해야 한다.
 * 아래의 클래스는 설정xml을 읽어들여 쿼리 수행에 필요한 객체를 얻기 위한 설정 객체이다.
 * Mybatis는 사실 내부적으로 개발자 대신 JDBC를 제어하고 있다.. 하지만 Mybatis를 쓸 경우
 * 개발자는 더이상 JDBC를 직접 제어하지 않는다. 대신 쿼리를 수행해주는 Mybatis가 제공하는
 * 객체를 사용해야 한다.
 * -> SqlSession
 * 따라서 이 객체는 db와의 CRUD를 담당하는 DAO가 취득하면 된다. 
  */
public class MybatisConfig {
	private static MybatisConfig instance;
	private SqlSessionFactory sqlSessionFactory;
	
	private MybatisConfig() {
		//패키지라 할지라도 , 대상 자원이 java 클래스가 아니면 , 일반 디렉토리 취급해야 한다...
		// 따라서 .으로 가지 말고 /로 가자.
		String resource = "mvcproject/mybatis/Mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
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
	
	//SqlSession을 반환하는 메서드 정의
	//SqlSession은 쿼리문을 수행해주는 객체 (Connection , PreparedStatement , ResultSet이 숨겨져있음. 편하게 개발.)
	public SqlSession getSqlSession() {
		
		return sqlSessionFactory.openSession();
		
	}
	
}
