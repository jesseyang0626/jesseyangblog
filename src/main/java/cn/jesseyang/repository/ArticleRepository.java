package cn.jesseyang.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jesseyang.domain.ArticleEntity;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity, Serializable>{
	public List<ArticleEntity> findAll();
	public ArticleEntity findById(int id);
	public long count();
	public Page<ArticleEntity> findAll(Pageable pageable);
	public void deleteArticleEntityById(int id);
}
