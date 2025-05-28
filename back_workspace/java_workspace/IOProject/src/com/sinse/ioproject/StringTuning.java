package com.sinse.ioproject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringTuning {
	
	FileInputStream fis;
	//효율성을 떠나 , 한글이 깨지지 않고 , 나오게 하기 위해 기존 스트림에 문자기반 스트림을 덧씌우자
	InputStreamReader reader;
	
	BufferedReader buffr;
	
	String name = "C:/lecture_workspace/back_workspace/java_workspace/IOProject/res/memo.txt";
	
	public StringTuning() {
		
		try {
			//빨대 업그레이드	
			fis = new FileInputStream(name);	
			reader = new InputStreamReader(fis);
			buffr = new BufferedReader(reader);
			
			String data = null;
			int count = 0 ;
			while(true) {
				data=buffr.readLine();
				if(data==null)break;
				count++;
				System.out.println(data);
			}
			System.out.println("총 읽은 횟수는"+count);
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(buffr != null) {
				try {
					buffr.close();
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	public static void main(String[] args) {
		new StringTuning();

	}

}