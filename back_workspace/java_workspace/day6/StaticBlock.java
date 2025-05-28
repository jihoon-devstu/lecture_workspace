class StaticBlock {
	static int x;
	
	//static 초기화 블럭
	//실행직전에 실행부 실행보다 먼저 초기화 블럭을 수행

	public static void main(String[] args){
		System.out.println("B");
	}
	
	static{
	x=7;
	System.out.println("A");
	}
}