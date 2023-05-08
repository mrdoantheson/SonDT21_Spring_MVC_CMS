package fa.traning.sondt21_spring_mvc_cms.repository;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends BaseRepository<Member, Long> {
    boolean existsMembersByEmail(String email);
}
