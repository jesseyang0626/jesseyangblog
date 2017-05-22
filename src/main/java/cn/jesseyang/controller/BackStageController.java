package cn.jesseyang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/backstage")
public class BackStageController {
	//主页 todo：显示文章列表
	@RequestMapping
	public String toBackStagePage(){
		return "backstage/index";
	}
}
