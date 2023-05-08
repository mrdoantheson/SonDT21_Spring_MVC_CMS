package fa.traning.sondt21_spring_mvc_cms.service;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;

import java.util.List;

public interface ContentService {
    List<Content> findAllContent();
    void create(Content content);
}
