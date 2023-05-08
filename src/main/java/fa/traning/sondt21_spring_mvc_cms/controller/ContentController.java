package fa.traning.sondt21_spring_mvc_cms.controller;

import fa.traning.sondt21_spring_mvc_cms.model.dto.ContentFromDto;
import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;
import fa.traning.sondt21_spring_mvc_cms.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cms")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/view-content")
    public String showContent(Model model) {
        List<Content> contents = contentService.findAllContent();
        model.addAttribute("contents", contents);
        return "content/ViewContents";
    }

    @GetMapping("/create-content")
    public String showCreateContent(Model model) {
        model.addAttribute("contentFromDto", new ContentFromDto());
        return "content/FormContent";
    }

    @PostMapping({"/create-content"})
    public String create(@Valid ContentFromDto contentFromDto) {
        Content content = new Content();
        BeanUtils.copyProperties(contentFromDto, content);
        contentService.create(content);

        return "redirect:/cms/view-content";
    }
}
