package fa.traning.sondt21_spring_mvc_cms.service;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Member;

import java.util.Optional;

public interface MemberService {
    void create(Member member);
    boolean existsEmail(String email);
    boolean existsEmailAndPassword(String email, String password);
    Optional<Member> findById(Long id);
    void update(Member member);
}
