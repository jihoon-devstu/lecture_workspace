package gui.io;
import java.io.*;


class  BinaryLoader{
	//실행중인 프로그램으로 데이터를 읽어들여야 하므로, 입력스트림 객체 필요.
	FileInputStream fis;
	FileOutputStream fos;
	String name = "C:/lecture_workspace/back_workspace/guiproject/res/pikachu.png";
	String target = "C:/lecture_workspace/back_workspace/guiproject/res/pikachu_copy.png";
	byte b = new byte[1204];
	
	
	public BinaryLoader(){
		try{
			fis = new FileInputStream(name);
			fos = new FileOutputStream(target);
			
			int data;
			
			while((data=fis.read(b)) != -1){
				System.out.println(data);
				//읽어들인 바이트 데이터를 내뱉는 빨대를 이용하여 출력해버리자...
				fos.write(data);
			}
			
		}catch(FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다.");
		}catch(IOException e){
			System.out.println("입출력 오류입니다.");
		}finally{
			if(fis != null){
				try{
					fis.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(fos != null){
				try{
					fos.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
		}
		
		
	}
	
	public static void main(String[] args) 
	{
		new BinaryLoader();
	}
}
