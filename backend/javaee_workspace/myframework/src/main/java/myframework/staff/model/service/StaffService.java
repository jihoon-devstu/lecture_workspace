package myframework.staff.model.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myframework.mybatis.MybatisConfig;
import myframework.staff.model.domain.Bio;
import myframework.staff.model.domain.Staff;
import myframework.staff.model.repository.BioDAO;
import myframework.staff.model.repository.StaffDAO;

//만일 이 서비스 객체가 없다면 , 컨트롤러가 모델 파트의 전문 영역을 담당해야하는 상황이 발생...
//이 경우 , 컨트롤러와 모델 영역의 경계가 모호해지므로(MVC 구조가 깨져버리므로) 
// 추후 컨트롤러가 교체되면 , 모델이 동작하지 않게 되어버림.
public class StaffService {
	MybatisConfig config = MybatisConfig.getInstance();
	Logger logger = LoggerFactory.getLogger(getClass());
	StaffDAO staffDAO = new StaffDAO();
	BioDAO bioDAO = new BioDAO();
	//CRUD
	public void regist(Bio bio) {
		//두개의 업무를 여기서 시킴...
		SqlSession sqlSession = config.getSqlSession();
		try {
			staffDAO.insert(sqlSession,bio.getStaff());
			bioDAO.insert(sqlSession,bio);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
}
