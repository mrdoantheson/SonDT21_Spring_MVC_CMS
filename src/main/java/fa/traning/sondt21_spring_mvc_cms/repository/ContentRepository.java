package fa.traning.sondt21_spring_mvc_cms.repository;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;

import java.util.List;

public interface ContentRepository extends BaseRepository<Content, Long> {
    List<Content> findAll();
}
