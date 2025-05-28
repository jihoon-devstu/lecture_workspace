package com.sinse.ioproject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KeyboardTest {

	public static void main(String[] args) {
		
		InputStream is = System.in; // 입력 스트림의 최상위 객체인 그냥 입력 스트림을 말함.
		InputStreamReader reader = new InputStreamReader(is); //파일 전용 , 키보드 , 스캐너 
		int data = -1;
		
		try {
			while(true) {
				data = reader.read(); 
				System.out.print((char)data);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
