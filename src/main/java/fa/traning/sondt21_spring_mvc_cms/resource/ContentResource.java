package fa.traning.sondt21_spring_mvc_cms.resource;

import fa.traning.sondt21_spring_mvc_cms.constant.AppConstant;
import fa.traning.sondt21_spring_mvc_cms.exception.ResourceFoundException;
import fa.traning.sondt21_spring_mvc_cms.model.dto.ContentDisplayDto;
import fa.traning.sondt21_spring_mvc_cms.model.dto.ContentFromDto;
import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;
import fa.traning.sondt21_spring_mvc_cms.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cms")
public class ContentResource {
    private final ContentService contentService;

    public ContentResource(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/view-content")
    public ResponseEntity<Page<ContentDisplayDto>> showContent(Model model,
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
        Specification<Content> specification = (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("deleted"), false);
        if (keywordOpt.isPresent()) {
            Specification<Content> specByKeyword = (root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("title"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("brief"), "%" + keywordOpt.get() + "%"),
                            criteriaBuilder.like(root.get("content"), "%" + keywordOpt.get() + "%")
                    );
            specification = specification.and(specByKeyword);
        }
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<Content> contentPage = contentService.findAllPaging(specification, pageRequest);
        List<ContentDisplayDto> displayDtos = contentPage.getContent().stream()
                .map(content -> {
                    ContentDisplayDto contentDisplayDto = new ContentDisplayDto();
                    BeanUtils.copyProperties(content, contentDisplayDto);
                    return contentDisplayDto;
                }).collect(Collectors.toList());
        Page<ContentDisplayDto> result = new PageImpl<>(displayDtos, pageRequest, contentPage.getTotalElements());
        return ResponseEntity.ok(result);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/view-content/delete/{id}")
    public void deleteEvent(@PathVariable Long id) {
        Optional<Content> contentOptional = contentService.findById(id);
        Content content = contentOptional.orElseThrow(ResourceFoundException::new);
        contentService.delete(content);
    }

    @GetMapping("/view-content/update/{id}")
    public ResponseEntity<ContentFromDto> showUpdate(@PathVariable Long id) {
        Optional<Content> content = contentService.findById(id);

        ContentFromDto contentFromDto = new ContentFromDto();
        BeanUtils.copyProperties(content.get(), contentFromDto);

        return ResponseEntity.ok(contentFromDto);
    }

    @PostMapping({"/view-content/update/{id}"})
    public ResponseEntity<ContentDisplayDto> getUpdate(@RequestBody @Valid ContentFromDto contentFromDto, @PathVariable Integer id) {
        Content content = new Content();
        BeanUtils.copyProperties(contentFromDto, content);
        content.setId(id);
        contentService.update(content);

        ResponseEntity.ok(content);

        ContentDisplayDto contentDisplayDto = new ContentDisplayDto();
        BeanUtils.copyProperties(content, contentDisplayDto);

        return ResponseEntity.ok(contentDisplayDto);
    }
}
