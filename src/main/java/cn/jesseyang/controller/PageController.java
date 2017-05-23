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
	//文章详情页
	@RequestMapping(value="/article")
	public String toPost(){
		return "article";
	}
	//页脚
	@RequestMapping(value="footer")
	public String footer(){
		return "fragment/footer";
	}
	//页头
	@RequestMapping(value="nav")
	public String nav(){
		return "fragment/nav";
	}
}
