package mall.notice.model;

import java.util.List;

import org.springframework.stereotype.Repository;

import mall.domain.Notice;

//애플리케이션 설계 분야에서 CRUD를 수행하는 역할을 가리켜 Repository라고 한다.
//@EnableWebMvc 에의 @Controller , @Repository , @Service , @Component 등을 찾아 메모리에 올린다.
//--> 생성하여 싱글턴으로 관리.
@Repository
public class MybatisNoticeDAO implements NoticeDAO{

	@Override
	public List selectAll() {
		return null;
	}

	@Override
	public Notice select(int notice_id) {
		return null;
	}

	@Override
	public void insert(Notice notice) {
		
	}

	@Override
	public void update(Notice notice) {
		
	}

	@Override
	public void delete(int notice_id) {
		
	}

}
