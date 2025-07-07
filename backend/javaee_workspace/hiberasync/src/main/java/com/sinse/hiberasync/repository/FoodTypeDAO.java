package com.sinse.hiberasync.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sinse.hiberasync.hibernate.HibernateConfig;
import com.sinse.hiberasync.model.FoodType;

public class FoodTypeDAO {
	HibernateConfig config= HibernateConfig.getInstance();

	
	//모든 업종 유형 가져오기
	public List selectAll() {
		Transaction tx = null;
		List list = null;
		
		/*java7부터 try~with-resource 문법이 지원됨.
		 자원을 사용한 후 , finally에서 닫는 중복코드를 단순화 시키고자 나온 문법
		 즉 언어 차원에서 개발자가 해야할 close() 메서드 호출을 자동으로 해줌.
		 !!! 주의 !!! 모든 close() 메서드를 대상으로 하지 않고 , Closeable을 구현한 객체만을 대상으로 함. 
		 		따라서 개발자는 try - catch 만 집중.
		 * */
		try(Session session = config.getSession()){
		
		tx = session.beginTransaction();
		
		TypedQuery<FoodType> query = session.createQuery("from FoodType",FoodType.class);
		list = query.getResultList();
		tx.commit();
		
		}catch() {
			
		}
		return list;
	}
}
