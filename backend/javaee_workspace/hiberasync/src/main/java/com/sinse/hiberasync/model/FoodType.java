package com.sinse.hiberasync.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/*[ORM] java의 Object와 Relation (관계형 db) 자체를 직접 Mapping / 즉 테이블과 java객체간의 매핑
 * JPA는 java의 자체 api에서 지원하는 인터페이스, 즉 java 표준
 * hibernate 사설로 JPA를 구현한 구현체
 */
@Data
@Entity //hibernate의 매핑객체
@Table(name="food_type") //hibernate의 매핑 대상 테이블
public class FoodType {
	@Id
	private int food_type_id;
	private String title;
	
}
