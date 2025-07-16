package mall.notice.model;

import java.util.List;

import org.springframework.stereotype.Repository;

import mall.domain.Notice;

@Repository
public class HibernateNoticeDAO implements NoticeDAO{

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
