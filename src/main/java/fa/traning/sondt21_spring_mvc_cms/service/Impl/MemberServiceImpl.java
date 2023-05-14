package fa.traning.sondt21_spring_mvc_cms.service.Impl;

import fa.traning.sondt21_spring_mvc_cms.model.entity.Member;
import fa.traning.sondt21_spring_mvc_cms.repository.MemberRepository;
import fa.traning.sondt21_spring_mvc_cms.service.MemberService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void create(Member member) {
        member.setCreateDate(LocalDateTime.now());
        member.setUpdateTime(LocalDateTime.now());
        memberRepository.save(member);
    }

    @Override
    public boolean existsEmail(String email) {
        Objects.requireNonNull(email);
        return memberRepository.existsMembersByEmail(email);
    }

    @Override
    public boolean existsEmailAndPassword(String email, String password) {
        Objects.requireNonNull(email, password);
        return memberRepository.existsMembersByEmailAndPassword(email, password);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public void update(Member member) {
        member.setUpdateTime(LocalDateTime.now());
        memberRepository.save(member);
    }
}
