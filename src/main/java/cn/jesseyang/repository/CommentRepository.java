package cn.jesseyang.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jesseyang.domain.CommentEntity;

public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Serializable>{
	public List<CommentEntity> findAll();
	public CommentEntity findById(int id);
	public long count();
	public Page<CommentEntity> findAll(Pageable pageable);
	public Page<CommentEntity> findByArticleId(String articleId,Pageable pageable);
}
