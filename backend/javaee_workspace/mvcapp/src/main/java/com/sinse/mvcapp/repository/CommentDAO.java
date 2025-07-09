package com.sinse.mvcapp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.mvcapp.exception.CommentException;
import com.sinse.mvcapp.model.Comment;
import com.sinse.mvcapp.mybatis.MybatisConfig;

public class CommentDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	//모든 댓글 가져오기
	public List selectAll() {
		return null;
	}
	
	
	//해당 뉴스 기사에 소속된 댓글만 가져오기
	public List selectByNewsId(int news_id) {
		SqlSession sqlSession = config.getSqlSession();
		List list = sqlSession.selectList("Comment.selectByNewsId", news_id);
		sqlSession.close();
		return list;
	}
	
	//레코드 1건 가져오기
	public Comment select(int comment_id) {
		return null;
	}
	
	//레코드 1건 입력하기
	public void insert(Comment comment) throws CommentException{
		SqlSession sqlSession = config.getSqlSession();
		int result = sqlSession.insert("Comment.insert", comment);
		
		if(result<1) {
			throw new CommentException("등록 실패");
		}
		sqlSession.commit();
		sqlSession.close();
	}
	
	//레코드 1건 수정하기
	public void update(Comment comment) {
		
	}
	
	//레코드 1건 삭제하기
	public void delete(int comment_id) {
		
	}
}
