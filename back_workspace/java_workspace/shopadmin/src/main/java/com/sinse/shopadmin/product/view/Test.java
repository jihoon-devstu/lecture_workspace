package com.sinse.shopadmin.product.view;

public class Test {
	public static void main(String[] args) {

		
		/*
		for (int i = 0; i < 5; i++) {
			System.out.println(System.currentTimeMillis());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}*/
		
		String path = "c://test/babo/mario.etst.jpg";
		
		//1) 가장 마지막점의 위치를 알아낸다. lastIndexOf(String str)
		//2) 가장 마지막점의 위치 바로 다음 위치부터 ~~ 가장 마지막 문자열까지 추출한다 substring(int beginIndex, int endIndex)
		
		int index = path.lastIndexOf(".");
		String ext = path.substring(index+1,path.length());
		System.out.println(ext);
	}
	
}
