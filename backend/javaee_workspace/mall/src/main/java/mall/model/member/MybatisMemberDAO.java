package mall.model.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.Member;
import mall.exception.MemberExistException;

@Repository
public class MybatisMemberDAO implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Member selectById(String id) {
		return sqlSessionTemplate.selectOne("Member.selectById",id);
	}

	@Override
	public void insert(Member member) throws MemberExistException{
		int result = sqlSessionTemplate.insert("Member.insert",member);
		if(result<1) {
			throw new MemberExistException("회원 등록 실패");
			
		}
	}

	@Override
	public Member selectByEmail(String email) throws MemberExistException{
		Member member = sqlSessionTemplate.selectOne("Member.selectByEmail", email);
		if(member==null) {
			throw new MemberExistException("일치하는 회원 정보가 없습니다");
		}
		return member;
	}

	@Override
	public Member login(Member member) throws MemberExistException{
		Member obj = sqlSessionTemplate.selectOne("Member.login",member);
		if(obj==null) {
			throw new MemberExistException("로그인 정보가 올바르지 않습니다");
		}
		return null;
	}

}
