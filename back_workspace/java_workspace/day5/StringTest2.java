class StringTest2{
	public static void main(String[] args){
	/*String은 불변의 특징이 있다.
	즉 변경될 수 없다... immutable 수정 불가하다.*/
	String x = "korea";
	for(int i=0; i<=100;i++){
		x=x+i;
		
	}
	System.out.println(x);
	//x = "korea fighting";
	//System.out.println(x);
	//}

	}
}