package fileserver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setCharacterEncoding("utf-8");
		//jsp에서 contenttype = 이랑 똑같음
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print("post 요청 받음");
		
		//파일 업로드 컴포넌트 중 cos.jar 써보자.
		int maxLimit = 1*1024*1024; //1MB
		MultipartRequest multi = new MultipartRequest(request,"C:\\lecture_workspace\\backend\\javaee_workspace\\testserver\\src\\main\\webapp\\data", maxLimit, "utf-8");
	}
}
