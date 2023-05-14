package fa.traning.sondt21_spring_mvc_cms.service;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ContentService {
    Page<Content> findAllPaging(Specification<Content> specification, Pageable pageable);
    void create(Content content);
    Optional<Content> findById(Long id);
    void delete(Content content);
    void update(Content content);
}
