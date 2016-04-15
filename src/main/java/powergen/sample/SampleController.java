package powergen.sample;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value="/common.do")
public class SampleController {

	private Logger logger = Logger.getLogger(SampleController.class);
	
	@RequestMapping(params="method=getIndex")
	public String getIndex() {
		logger.info("execute getIndex");
		return "sampleIndex";
	}
}