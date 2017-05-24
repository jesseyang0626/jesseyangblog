package cn.jesseyang.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jesseyang.domain.ArticleEntity;
import cn.jesseyang.domain.ArticleTypeEntity;

public interface ArticleTypeRepository extends PagingAndSortingRepository<ArticleTypeEntity, Serializable>{
	public List<ArticleTypeEntity> findAll();
	public ArticleTypeEntity findById(int id);
	public long count();
	public Page<ArticleTypeEntity> findAll(Pageable pageable);
	public void deleteArticleEntityById(int id);
}
