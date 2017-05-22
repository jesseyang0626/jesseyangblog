package cn.jesseyang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToPageController {

	@RequestMapping("/")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/articleList")
	public String toArtcileListPage(){
		return "articleList";
	}
	
	@RequestMapping("/erweima")
	public String toCodePage(){
		return "erweima";
	}
}
