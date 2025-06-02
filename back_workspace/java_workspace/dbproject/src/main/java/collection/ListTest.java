package collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList(); //고무줄 배열 (JS랑 동일)
		
		list.add("apple");
		list.add("banana");
		list.add("grape");
		list.add("orange");
		
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		for(Object obj : list) {
			System.out.println(obj);
		}
	}

}

