package cn.jesseyang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jesseyang.domain.ArticleTypeEntity;
import cn.jesseyang.service.articleType.ArticleTypeService;

@RestController
@RequestMapping(value="articleType")
public class ArticleTypeController {
	@Autowired
	private ArticleTypeService articleTypeService;
	
	@RequestMapping(value="list")
	public List<ArticleTypeEntity> list(){
		return articleTypeService.getAll();
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteById(@PathVariable int id){
		articleTypeService.deleteById(id);
	}
	
	@PostMapping(value="add")
	public void save(@RequestBody ArticleTypeEntity entity){
		articleTypeService.save(entity);
		
	}
}
