package fa.traning.sondt21_spring_mvc_cms.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Member extends AbstractAuthenticationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String username;
    @Column
    private String password;
    @Column(unique = true)
    @Size(min = 10, max = 10)
    @Pattern(regexp = "(01|03|05|07|08|09)+([0-9]{8})\\b", message = "Phone number is not valid")
    private String phone;
    @Column(unique = true)
    @Email
    private String email;
    private String description;
    @OneToMany(mappedBy = "member")
    private List<Content> contents;
}
