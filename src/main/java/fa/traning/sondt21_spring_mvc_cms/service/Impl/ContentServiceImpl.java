package fa.traning.sondt21_spring_mvc_cms.service.Impl;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;
import fa.traning.sondt21_spring_mvc_cms.repository.ContentRepository;
import fa.traning.sondt21_spring_mvc_cms.service.ContentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;

    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public List<Content> findAllContent() {
        return (List<Content>) contentRepository.findAll();
    }

    @Override
    public void create(Content content) {
        content.setCreateDate(LocalDateTime.now());
        content.setUpdateTime(LocalDateTime.now());
        contentRepository.save(content);
    }
}
