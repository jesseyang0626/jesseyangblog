package cn.jesseyang.service.article;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import cn.jesseyang.domain.ArticleEntity;
import cn.jesseyang.repository.ArticleRepository;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Override
	public List<ArticleEntity> getAll() {
		
		return articleRepository.findAll();
	}
	@Override
	@Transactional
	public void save(ArticleEntity articleEntity) {
		articleRepository.save(articleEntity);
	}
	
	@Override
	public ArticleEntity findById(int id) {
		return articleRepository.findById(id);
	}
	@Override
	public Page<ArticleEntity> findArticleEntityByPageable(int page,int num) {
		Sort sort = new Sort(Direction.DESC,"id");
		Pageable pageable = new PageRequest(page-1, num,sort);
		return articleRepository.findAll(pageable);
	}
	@Override
	@Transactional
	public void deleteById(int id) {
		articleRepository.deleteArticleEntityById(id);
	}

}
