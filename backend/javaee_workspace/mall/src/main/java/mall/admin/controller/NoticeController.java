package mall.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NoticeController {
	
	//목록 요청 , 특정 uri에 매핑되는 대상을 컨트롤러 클래스로 처리하는 것이 아니라
	//메서드로 처리하기 위함.
	@RequestMapping("/notice/list")
	public List selectAll() {
		log.debug("목록 요청 받음");
		return null;
	}
	//상세보기 요청
	//글 등록 요청
	//글 수정 요청
	//글 삭제 요청
	
}
