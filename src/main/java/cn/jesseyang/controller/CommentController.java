package cn.jesseyang.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jesseyang.domain.ArticleEntity;
import cn.jesseyang.domain.CommentEntity;
import cn.jesseyang.service.comment.CommentService;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/add")
	public String addComment(HttpServletRequest request){
		String articleId = request.getParameter("articleId");
		String nickname = request.getParameter("name");
		String content = request.getParameter("message");
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setArticleId(articleId);
		commentEntity.setContent(content);
		commentEntity.setNickname(nickname);
		commentEntity.setCreateDate(new Date());
		commentService.save(commentEntity);
		return "redirect:/article/"+articleId;
	}
	
	@RequestMapping(value="/findByPage/{page}")
	@ResponseBody
	public Page<CommentEntity> findByPage(@PathVariable int page,HttpServletRequest hsr){
		HttpSession session = hsr.getSession();
		ArticleEntity articleEntity = (ArticleEntity) session.getAttribute("article");
		int articleId = articleEntity.getId();
		Sort sort = new Sort(Direction.DESC,"id");
		Pageable pageable = new PageRequest(page-1, 10,sort);
		Page<CommentEntity> comments = commentService.findByArticleId(articleId+"", pageable);
		return comments;
	}
}
