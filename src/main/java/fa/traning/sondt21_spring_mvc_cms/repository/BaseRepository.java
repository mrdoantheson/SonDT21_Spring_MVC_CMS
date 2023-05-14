package fa.traning.sondt21_spring_mvc_cms.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends CrudRepository<T, ID>, JpaSpecificationExecutor<T> {
    Optional<T> findByIdAndDeletedFalse(ID id);
}
