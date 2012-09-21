package kr.oks.saboard.controller;

import kr.oks.saboard.constants.Constants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:"+Constants.URL_BOARD_LIST;
	}
}
