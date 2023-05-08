package fa.traning.sondt21_spring_mvc_cms.model.dto;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Content;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberFromDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String description;
    private List<Content> contents;
}
