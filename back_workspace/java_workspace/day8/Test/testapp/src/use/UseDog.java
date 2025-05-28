/*다른 클래스를 사용하기 위한 클래스이므로, 실행부 정의하자*/

package use;

import animal.Dog; //classpath 환경변수를 기준으로.. 그 밑의 animal 밑의 
					// dog.class를 임포트 한다 !!
					

//개발자가 패키지를 선언하면 javac -d 옵션 사용시 선언하 ㄴ패키지에
//해당하는 디렉토리를 자동 생성해줌

class UseDog{
	public static void main(String[] args){
		Dog d = new Dog();
		d.bark();

	}
		
}