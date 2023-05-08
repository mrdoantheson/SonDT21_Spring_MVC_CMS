package fa.traning.sondt21_spring_mvc_cms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Content extends AbstractAuthenticationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    private String brief;
    @Column(length = 1000, nullable = false)
    private String content;
    private String sort;
    @ManyToOne
    @JoinColumn(name = "AuthorId")
    private Member member;
}
