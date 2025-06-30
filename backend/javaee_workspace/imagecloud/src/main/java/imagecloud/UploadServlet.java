package imagecloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

//클라이언트가 전송한 텍스트 데이터 , 바이너리 데이터까지 처리해보기
public class UploadServlet extends HttpServlet{
	
	 //바이너리 파일이 저장 될 서버의 경로
	String savePath ="C:/lecture_workspace/backend/javaee_workspace/imagecloud/src/main/webapp/public";
	int maxLimit=3*1024*1024; //유지 보수 목적 상 , 계산 결과를 쓰지 말고 , 풀어서 표기하는게 좋음 (앞자리만 바꾸면 mb단위를 바꿀 수 있으므로)
	
	
	//클라이언트가 상당히 많은양의 바이너리로 요청을 시도하므로, 당연히 post 로 전송을 하기 떄문에
	// doXXX 형 메서드 중  doPost로 처리한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); //전송되어지는 파라미터의 값들이 깨지지 않도록 인콛징 지정
		response.setContentType("text/html;charset=utf-8"); // JSP에서의 Page 지시 영역과 동일
		
		PrintWriter out = response.getWriter();
		
		//업로드 처리
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, maxLimit,"utf-8");
			System.out.println("업로드 완료");
			
			//텍스트 파라미터 추출 , 파일 업로드 컴포넌트를 이용하면 , 파라미터 추출 마저도 업로드 컴포넌트를 이용 !!
			
			String title = multi.getParameter("title");
			System.out.println("전송된 제목은" + title);
			out.print("전송된 제목은" + title);
			
			
			//[파일 처리 관련]
			//1) 이미 서버에 저장되어버린 , 이미지를 개발자가 원하는 이미지로 교체...
			//2) 클라이언트측에서 파일명을 결정짓고 업로드 하면 , 이 과정은 불필요...
			
			/*
			 * collection framework 란?
			 *  객체를 모아서 처리할때 , 효율적으로 처리할 수 있도록 지원되는 java util 패키지에서 지원하는 api
			 *  
			 *  모아진 모습은 ? 
			 *  1) 순서있는 모음 - 대표적인 모습은 배열이지만 , 배열은 기본 자료형도 제어하므로 , 즉 객체만을 다루진 않음
			 *  						따라서 컬렉션 프레임웍은 아니다...
			 *  						List 
			 *  
			 *  2)순서 없는 모음 - Set
			 *  					 - Map  (key - value 쌍) 
 			 * */
			
			//순서 없는 컬렉션 객체를 처리할 때 사용되는 도구가 2가지 유형이 있다. (Enumeration , Iterator)
			Enumeration<String> en = multi.getFileNames();
			while(en.hasMoreElements()) {
				String param = en.nextElement();
				out.print("업로드 된 파라미터명은" + param);
				System.out.println("업로드된 파일명은" + param);
				
				
				//파라미터 명을 이용하면 , 업로드된 파일명도 추출 가능
				multi.getOriginalFileName(param);
			}
			
		} catch (IOException e) {
			System.out.println("업로드 실패");
			e.printStackTrace();
		}
	
	}
	
}
