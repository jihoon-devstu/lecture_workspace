package com.sinse.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamStudy {

	String path = "C:/public/data.txt";

	// 바이트 기반의 스트림으로 읽기
	public void readByte() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			while (true) {
				int data = -1;
				data = fis.read();
				if (data == -1)
					break;
				System.out.print((char) data);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// 문자 기반의 스트림으로 읽기
	public void readReader() {
		FileInputStream fis = null; // 바이트 기반
		InputStreamReader reader = null;

		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);

			while (true) {
				int data = -1;
				data = reader.read();
				if (data == -1)
					break;
				System.out.print((char)data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// 버퍼 기반의 스트림으로 읽기
	public void readBuffer() {
		FileInputStream fis = null;
		InputStreamReader reader = null;
		BufferedReader buffr = null;
		
		String data = null;
		
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			buffr = new BufferedReader(reader);
			
			while(true) {
				data = buffr.readLine();
				
				if(data == null)break;
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(buffr !=null) {
				try {
					buffr.close();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		StreamStudy s = new StreamStudy();
		s.readBuffer();
	}
}
