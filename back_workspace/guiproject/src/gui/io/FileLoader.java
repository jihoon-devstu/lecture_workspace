package gui.io;
import java.io.*;


class  FileLoader{
	
	public static void main(String[] args) {
		String name = "C:/lecture_workspace/back_workspace/guiproject/res/memo.txt";
		FileInputStream fis = null;
		try{
			 fis = new FileInputStream(name);
			int data;
			/*while(true){
				data = fis.read();
				if(data==-1)break;
				System.out.print((char)data);
			};*/
			
			while((data=fis.read()) != -1){
				
				System.out.print((char)data);
			}
			fis.close();
			
		}catch(FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다.");
		}catch(IOException e){
			System.out.println("내용을 찾을 수 없습니다.");
		}finally{
			if(fis !=null){
				try{
				fis.close();
				}catch(IOException e){
					//일반 유저가 아닌, 개발자를 위한 log 출력
					e.printStackTrace();
				}
			}
		}
		
	}
}
