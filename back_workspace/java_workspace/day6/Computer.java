class  Computer{
	String company="애플";
	int price=130;

	public int getPrice(){
		return price;
	}
	public void setValue(String c, int p){
		company=c;
		price=p;
	}
}

class UseComputer{

	public static void main(String[] args){
		Computer com=new Computer();
		com.setValue("삼성",200);
		int a=com.price;
		int b=com.getPrice();
		System.out.println("a+b 결과는 "+(a+b));
	}
}


