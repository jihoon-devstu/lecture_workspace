package com.sinse.boardapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mysql.cj.protocol.Resultset;
import com.sinse.boardapp.exception.NoticeException;
import com.sinse.boardapp.model.Notice;
import com.sinse.boardapp.mybatis.MybatisConfig;

public class NoticeDAO {

	MybatisConfig config = MybatisConfig.getInstance();
	
	// 모든 레코드 가져오기
	public List selectAll() {
		SqlSession sqlSession = config.getSqlSession();
		List list = sqlSession.selectList("com.sinse.boardapp.model.Notice.selectAll");
		sqlSession.close();
		return list;
	}
	
	//한건 가져오기
	public Notice select(int notice_id) {
		SqlSession sqlSession = config.getSqlSession();
		Notice notice = sqlSession.selectOne("com.sinse.boardapp.model.Notice.select", notice_id);
		sqlSession.close();
		
		return notice;
	}
	
	//한건 넣기
	public void insert(Notice notice) throws NoticeException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("com.sinse.boardapp.model.Notice.insert", notice);
		sqlSession.commit(); //DML의 트랜젝션 확정
		if(result <1) {
			throw new NoticeException("등록 실패");
		}
		sqlSession.close();
	}
	
	//한건 수정
	public void update(Notice notice)throws NoticeException {
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.update("com.sinse.boardapp.model.Notice.update", notice);
		sqlSession.commit();
		if(result <1 ) {
			throw new NoticeException("수정 실패");
		}
		sqlSession.close();
	}
	
	//한건 삭제
	public void delete(Notice notice) {
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.delete("com.sinse.boardapp.model.Notice.delete", notice);
		sqlSession.commit();
		if(result <1) {
			throw new NoticeException("삭제 실패");
		}
		sqlSession.close();
	}
	
}
