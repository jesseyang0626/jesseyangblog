package cn.jesseyang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping(value="/")
	public String toIndex(){
		return "index";
	}
}
