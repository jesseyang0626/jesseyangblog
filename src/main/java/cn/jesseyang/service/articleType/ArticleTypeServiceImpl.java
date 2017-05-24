package cn.jesseyang.service.articleType;

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
import cn.jesseyang.domain.ArticleTypeEntity;
import cn.jesseyang.repository.ArticleRepository;
import cn.jesseyang.repository.ArticleTypeRepository;

@Service("articleTypeService")
public class ArticleTypeServiceImpl implements ArticleTypeService {

	@Autowired
	private ArticleTypeRepository articleTypeRepository;

	@Override
	public List<ArticleTypeEntity> getAll() {
		
		return articleTypeRepository.findAll();
	}

	@Override
	public void save(ArticleTypeEntity articleEntity) {
		articleTypeRepository.save(articleEntity);
	}

	@Override
	public ArticleTypeEntity findById(int id) {
		// TODO Auto-generated method stub
		return articleTypeRepository.findById(id);
	}

	@Override
	public Page<ArticleTypeEntity> findArticleEntityByPageable(int page, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		articleTypeRepository.deleteArticleEntityById(id);
	}


}
