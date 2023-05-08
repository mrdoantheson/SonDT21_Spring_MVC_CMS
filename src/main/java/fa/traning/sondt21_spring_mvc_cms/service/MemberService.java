package fa.traning.sondt21_spring_mvc_cms.service;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Member;

public interface MemberService {
    void create(Member member);
    boolean existsEmail(String email);
}
