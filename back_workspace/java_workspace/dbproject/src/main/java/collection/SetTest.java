package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {
	//컬렉션 프레임웍이 지원하는 순서 없는 객체중 하나인 Set을 학습
	
	public static void main(String[] args) {
		Set<String> set = new HashSet<>(); //앞에<String>을 적으면 , 뒤에<>에는 자료형 적을 필요 없음 !  
		
		set.add("BMW");
		set.add("Benz");
		set.add("Audi");
		set.add("K9");
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			String obj = it.next();
			System.out.println(obj);
		}
		
	}
}
