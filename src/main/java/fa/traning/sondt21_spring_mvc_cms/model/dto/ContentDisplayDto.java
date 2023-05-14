package fa.traning.sondt21_spring_mvc_cms.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ContentDisplayDto {
    private Integer id;
    private String title;
    private String brief;
    private String content;
    private LocalDateTime createDate;
}
