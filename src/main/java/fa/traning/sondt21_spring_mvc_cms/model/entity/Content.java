package fa.traning.sondt21_spring_mvc_cms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Content extends AbstractAuthenticationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    private String brief;
    @Column(length = 1000, nullable = false)
    private String content;
    private String sort;
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name = "AuthorId")
    private Member member;
}
