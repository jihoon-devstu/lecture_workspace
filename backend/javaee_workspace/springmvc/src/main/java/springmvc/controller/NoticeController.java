package springmvc.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Component //스프링이 메모리에 올릴 대상이며 , WebConfig에서 Scan을 통해 올라감.
public class NoticeController {

	@RequestMapping("/notice/list")
	public ModelAndView selectAll() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/list");
		return mav;
	}
}
