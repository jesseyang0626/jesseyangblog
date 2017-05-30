package cn.jesseyang.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jesseyang.domain.ArticleEntity;
import cn.jesseyang.domain.CommentEntity;
import cn.jesseyang.service.article.ArticleService;
import cn.jesseyang.service.comment.CommentService;
import cn.jesseyang.utils.JsonUtils;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	

	
	@RequestMapping("/getAll")
	@ResponseBody
	public String getAll(){
		return JsonUtils.toString(articleService.getAll());
	}
	
	@RequestMapping("/getArticlesByPage/{page}")
	@ResponseBody
	public Page<ArticleEntity> getArticlesByPage(@PathVariable int page){
		return articleService.findArticleEntityByPageable(page,10);
	}
	@RequestMapping(value="/page/{page}",method=RequestMethod.GET)
	@ResponseBody
	public Page<ArticleEntity> findByPage(@PathVariable int page){
		return articleService.findArticleEntityByPageable(page,10);
	}
	//添加文章
	@RequestMapping("/add")
	@ResponseBody
	public String addArticle(@RequestBody ArticleEntity articleEntity){
		try{
			Date date = new Date();
			articleEntity.setCreateDate(date);
			articleService.save(articleEntity);
		}catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		return "1";
		
	}
	
	@RequestMapping("/single")
	public String toSingle(){
		return "single";
	}
	
	//根据id获取文章、评论详情
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  
	public String findOwner(@PathVariable String id,HttpServletRequest hsr) {
	  HttpSession session = hsr.getSession();
	  ArticleEntity articleEntity = articleService.findById(Integer.valueOf(id));
	  session.setAttribute("article", articleEntity);
/*	  Sort sort = new Sort(Direction.DESC,"id");
	  Page<CommentEntity> comments = commentService.findByArticleId(id, 0);
	  session.setAttribute("comments", comments);*/
	  return "articleDetail";   
	}  
	
	@DeleteMapping(value="/{id}")
	@ResponseBody
	public String deleteById(@PathVariable int id){
		articleService.deleteById(id);
		return "1";
	}
	//得到最新的6个文章
	@RequestMapping("/getTop6")
	@ResponseBody
	public Page<ArticleEntity> getTop6(){
		return articleService.findArticleEntityByPageable(1,6);
	}
}
