package fa.traning.sondt21_spring_mvc_cms.model.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@MappedSuperclass
public abstract class AbstractAuthenticationEntity {
    private LocalDateTime createDate;
    private LocalDateTime updateTime;
}
