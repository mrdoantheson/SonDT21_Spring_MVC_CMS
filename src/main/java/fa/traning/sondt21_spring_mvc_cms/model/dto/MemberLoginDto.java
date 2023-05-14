package fa.traning.sondt21_spring_mvc_cms.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberLoginDto {
    private String email;
    private String password;
}
