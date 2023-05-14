package fa.traning.sondt21_spring_mvc_cms.service.Impl;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;
import fa.traning.sondt21_spring_mvc_cms.repository.ContentRepository;
import fa.traning.sondt21_spring_mvc_cms.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;

    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Page<Content> findAllPaging(Specification<Content> specification, Pageable pageable) {
        return contentRepository.findAll(specification, pageable);
    }

    @Override
    public void create(Content content) {
        content.setCreateDate(LocalDateTime.now());
        content.setUpdateTime(LocalDateTime.now());
        contentRepository.save(content);
    }

    @Override
    public Optional<Content> findById(Long id) {
        return contentRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public void delete(Content content) {
        content.setDeleted(true);
        content.setUpdateTime(LocalDateTime.now());
        contentRepository.save(content);
    }

    @Override
    public void update(Content content) {
        content.setUpdateTime(LocalDateTime.now());
        contentRepository.save(content);
    }
}
