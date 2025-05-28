/*변수와 자료형 */


class Test2{
	//자바의 실행부임. 이 함수가 없다면 java.exe의 대상이 될 수 없음.
	//즉 실행될 수 없다.
	public static void main(String[]args){
		/*java도 기존의 언어의 전통을 이어받았기 때문에, 기본 자료형은 문자,숫자,논리값을 지원함*/
		
		//자바의 문자는 문자와 문자열로 구분
		//자바의 문자 자료형 char (character)
		char a = '우'; // 한글자를 문자형이라고 한다.
		String str="대한민국"; // 두글자 이상의 문자 집합을 문자열이라 한다.
		
		//자바의 논리값은 js 및 다른 언어와 동일함 (true , false)
		boolean b = true; //주의 - 다른 언어에서는 1이 true , 0이 false 를
								// 대신할 수 있지만 , java는 철저히 true or false로 표기해야함
								
		/*java의 숫자형은 크게 정수와 소숫점을 지원하는 실수로 구분*/
		//정수는 용량에 따라 byte < short < int < long
		//실수는 float < double
		
		int x = 76;
		
		float y=5.6f; // 자바에서는 개발자가 소수점만 적으면 무조건 double 로 생각함 따라서 f 붙여줘야함
		
		System.out.println(a);
	}
}
