package springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller //스프링이 메모리에 올릴 대상이며 , WebConfig에서 Scan을 통해 올라감.
public class NoticeController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/notice/list")
	public ModelAndView selectAll() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list","게시물 목록"); //request.setAttribute("list","게시물 목록") 과 동일
		mav.setViewName("notice/list");
		return mav;
	}
	
	//글쓰기 폼 요청 처리(원래 jsp를 webapp 바로 밑에 두었다면 , 매핑이 필요 없음. 즉 직접 접근 가능 함.)
	//하지만 , spring mvc 에서는 jsp 마저도 직접 접근을 막고 controller mapping으로 처리함.
	
	@RequestMapping("/notice/registform")
	public String registForm() {
		return "notice/write";
	}
	
	
	//메서드 호출 후 , 반환할 값이 없을 때 , 즉 저장할 것이 없을 때는
	//ModelAndView 중 View만 반환하면 되므로 , 이때는 String으로 대체해도 됨.
	//예) notice/detail --> String 을 넘겨받은 DispatcherServlet 이 /WEB-INF/views/notice/detail.jsp
	@RequestMapping(value = "/notice/regist", method = RequestMethod.POST)
	public String regist() {
		logger.debug("글쓰기 요청 받음");
		//개발자가 redirect를 명시하지 않으면 스프링은 default가 foward임.
		return "redirect:/shop/notice/list";
	}
}
