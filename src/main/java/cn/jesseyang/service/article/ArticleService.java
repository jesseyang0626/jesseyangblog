package cn.jesseyang.service.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.jesseyang.domain.ArticleEntity;

public interface ArticleService {
	public List<ArticleEntity> getAll();
	public void save(ArticleEntity articleEntity);
	public ArticleEntity findById(int id);
	public Page<ArticleEntity> findArticleEntityByPageable(int page,int num);
	public void deleteById(int id);
}
