package mall.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Member;
import mall.exception.MemberExistException;
import mall.exception.PasswordEncryptException;
import mall.util.PasswordUtil;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private PasswordUtil passwordUtil;

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public Member selectById(String id) throws MemberExistException{
		return memberDAO.selectById(id);
	}

	@Override
	public void regist(Member member) throws PasswordEncryptException{
		
		if(member.getSnsProvider()==null) {
			//홈페이지 회원인 경우
			String salt = PasswordUtil.generateSalt();
			
			//전송된 파라미터와 salt를 섞어서 해시 생성
			String hashedPassword = passwordUtil.hashPassword(member.getPassword(), salt);
			
			//Member모델에 덮어쓰기
			member.setSalt(salt);
			member.setPassword(hashedPassword);
			
		}
		
		memberDAO.insert(member);
		
	}

	@Override
	public Member login(Member member) throws MemberExistException{
		
		Member obj = memberDAO.selectByEmail(member.getEmail());
		log.debug("db에 저장된 salt는 " + obj.getSalt());
		log.debug("db에 저장된 salt와 파라미터로 전송된 비번과의 조합 해시는" + passwordUtil.hashPassword(member.getPassword(), obj.getSalt()));
		log.debug("db에 저장되어있었던 비번은 " + obj.getPassword());
		
		String dbHash = passwordUtil.hashPassword(member.getPassword(), obj.getSalt());
		
		if(dbHash.equals(obj.getPassword()) == false) {
			throw new MemberExistException("로그인 정보가 올바르지 않습니다");
		}
		
		return obj;
	}

}
