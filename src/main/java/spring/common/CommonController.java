package spring.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="/common.do")
public class CommonController {

	@RequestMapping(params="method=getIndex")
	public String getIndex() {
		return "index";
	}
	
}