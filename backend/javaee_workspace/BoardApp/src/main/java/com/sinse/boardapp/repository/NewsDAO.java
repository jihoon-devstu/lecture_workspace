package com.sinse.boardapp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.boardapp.exception.NewsException;
import com.sinse.boardapp.model.News;
import com.sinse.boardapp.mybatis.MybatisConfig;

//DAO의 존재는 그대로 유지 하되, DAO의 CRUD 메서드 안에 상투적 JDBC코드를 작성하지 말자.
//Mybatis 프레임웍 (SQL Mapper)과 Hibernate(ORM) 프레임웍을 이용하기 때문에....
public class NewsDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	//모든 레코드 가져오기
	public List selectAll() {
		SqlSession sqlSession = config.getSqlSession();
		List list = sqlSession.selectList("News.selectAll");
		sqlSession.close();
		return list;
	}
	
	//레코드 1건 가져오기
	public News select(int news_id) {
		SqlSession sqlSession = config.getSqlSession();
		News news = sqlSession.selectOne("News.select", news_id);
		sqlSession.close();
		return news;
	
	}
	
	//레코드 1건 입력하기
	public void insert(News news) throws NewsException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("News.insert", news);
		
		if(result<1) {
			throw new NewsException("등록 실패");
		}
		sqlSession.commit();
		sqlSession.close();
	}
	
	//레코드 1건 수정하기
	public void update(News news) {
		
	}
	
	//레코드 1건 삭제하기
	public void delete(int news_id) {
		
	}
	
}
