package fa.traning.sondt21_spring_mvc_cms.model.dto;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentFromDto {
    private String title;
    private String brief;
    private String content;
}
