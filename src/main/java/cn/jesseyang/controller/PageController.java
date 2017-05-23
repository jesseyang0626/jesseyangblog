package cn.jesseyang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping(value="/")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping(value="backstage")
	public String toBackstage(){
		return "backstage/backstage";
	}
	//页脚
	@RequestMapping(value="footer")
	public String footer(){
		return "footer";
	}
}
