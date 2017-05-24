package cn.jesseyang.service.articleType;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.jesseyang.domain.ArticleEntity;
import cn.jesseyang.domain.ArticleTypeEntity;

public interface ArticleTypeService {
	public List<ArticleTypeEntity> getAll();
	public void save(ArticleTypeEntity articleEntity);
	public ArticleTypeEntity findById(int id);
	public Page<ArticleTypeEntity> findArticleEntityByPageable(int page,int num);
	public void deleteById(int id);
}
