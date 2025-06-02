package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	
	public static void main(String[] args) {
		Map <String, String>map = new HashMap();
		
		map.put("a1", "가나초콜릿");
		map.put("a2", "허쉬초콜릿");
		map.put("a3", "페레로로쉐");
		
		Set set = map.keySet();
		Iterator<String> it = set.iterator();
		
		
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key); //기존 맵에서 Key 이용하여 접근
			
			System.out.println(key +","+value);
		}
	}
}
