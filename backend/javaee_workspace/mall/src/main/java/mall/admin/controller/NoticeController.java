package mall.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import mall.notice.model.NoticeService;

@Slf4j
@Controller
public class NoticeController {
	
	//규모가 작은 프로젝트에서는 상관 없으나 , 규모가 엄청 큰 대규모의 경우에는 
	//유지 보수가 시간이 곧 비용이므로 , 최대한 유지보수성을 높이려면 객체와 객체들간의 has a 관계는
	//느슨할수록 좋다 !!! (상위 자료형으로 보유할 수록 느슨해진다...)
	
	//스프링 컨테이너로부터 인스턴스 받기.
	@Autowired
	NoticeService noticeService;
	
	//목록 요청 , 특정 uri에 매핑되는 대상을 컨트롤러 클래스로 처리하는 것이 아니라
	//메서드로 처리하기 위함.
	@RequestMapping("/notice/list")
	public ModelAndView selectAll() {
		//3단계 : db에서 가져오기
		//4단계 : 결과 저장
		//ModelAndView 객체는 Model과 View를 합쳐놓은 객체임.
		//Model 객체에 정보를 담으면 request.setAttribute() 와 동일한 효과.
		//View에는 DispatcherServlet에게 전달할 페이지파일명이 아닌 , 이름을 전달
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/list");
		log.debug("목록 요청 받음");
		
		noticeService.selectAll();
		return mav;
	}
	//상세보기 요청
	//글 등록 요청
	//글 수정 요청
	//글 삭제 요청
	
}
