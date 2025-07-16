package mall.notice.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.Notice;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDAO noticeDAO;
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice select(int notice_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(Notice notice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Notice notice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int notice_id) {
		// TODO Auto-generated method stub
		
	}

}
