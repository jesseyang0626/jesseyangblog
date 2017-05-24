package cn.jesseyang.service.comment;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import cn.jesseyang.domain.CommentEntity;
import cn.jesseyang.repository.CommentRepository;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Override
	public List<CommentEntity> getAll() {
		
		return commentRepository.findAll();
	}
	@Override
	@Transactional
	public void save(CommentEntity CommentEntity) {
		commentRepository.save(CommentEntity);
	}
	
	@Override
	public CommentEntity findById(int id) {
		return commentRepository.findById(id);
	}
	@Override
	public Page<CommentEntity> findCommentEntityByPageable(Pageable pageable) {
		return commentRepository.findAll(pageable);
	}
	@Override
	public Page<CommentEntity> findByArticleId(String articleId,int page) {
		Sort sort = new Sort(Direction.DESC,"id");
		Pageable pageable = new PageRequest(page-1, 10,sort);
		return commentRepository.findByArticleId(articleId, pageable);
	}

}
