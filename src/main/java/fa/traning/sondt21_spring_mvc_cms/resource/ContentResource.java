package fa.traning.sondt21_spring_mvc_cms.resource;

import fa.traning.sondt21_spring_mvc_cms.constant.AppConstant;
import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;
import fa.traning.sondt21_spring_mvc_cms.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cms")
public class ContentResource {
    private final ContentService contentService;

    public ContentResource(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/view-content")
    public ResponseEntity<Page<Content>> showContent(Model model,
                                      @RequestParam(required = false, name = "q") Optional<String> keywordOpt,
                                      @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_STR) Integer page,
                                      @RequestParam(required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) Integer size,
                                      @RequestParam(required = false, name = "sort",
                                      defaultValue = AppConstant.DEFAULT_SORT_FIELD) List<String> sorts) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sortField : sorts) {
            boolean isDesc = sortField.startsWith("-");
            orders.add(isDesc ? Sort.Order.desc(sortField.substring(1))
                    : Sort.Order.asc(sortField));
        }
        Specification<Content> specification = (root, query, criteriaBuilder) -> {
            if (keywordOpt.isPresent()) {
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + keywordOpt.get() + "%"),
                        criteriaBuilder.like(root.get("brief"), "%" + keywordOpt.get() + "%"),
                        criteriaBuilder.like(root.get("content"), "%" + keywordOpt.get() + "%")
                );
            } else {
                return criteriaBuilder.conjunction();
            }
        };
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<Content> contentPage = contentService.findAllPaging(specification, pageRequest);
        model.addAttribute("contentPage", contentPage);
        return ResponseEntity.ok(contentPage);
    }
}
