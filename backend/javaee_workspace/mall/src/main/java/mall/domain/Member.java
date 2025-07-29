package mall.domain;

import lombok.Data;

@Data
public class Member {

	private int member_id;
	private SnsProvider snsProvider;
	private String id; //sns의 id가 비록 숫자이지만 , 홈페이지 회원이 문자열이므로...
	private String password;
	private String salt;
	private String name; //홈페이지 회원은 실명일 수 있으나 , sns회원은 거의 실명이 없다.
	private String email; //sns 업체 정책에 따라 이메일 제공 안할수도 있다... 따라서 null 가능
	private String regdate;
}
