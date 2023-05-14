package fa.traning.sondt21_spring_mvc_cms.service.Impl;

import fa.traning.sondt21_spring_mvc_cms.model.dto.MemberLoginDto;
import fa.traning.sondt21_spring_mvc_cms.service.MemberService;
import fa.traning.sondt21_spring_mvc_cms.service.MemberValidationService;
import org.springframework.stereotype.Service;

@Service
public class MemberValidationServiceImpl implements MemberValidationService {
    private final MemberService memberService;

    public MemberValidationServiceImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public String validateUser(MemberLoginDto memberLoginDto) {
        String message = "";
        if (!memberService.existsEmailAndPassword(memberLoginDto.getEmail(), memberLoginDto.getPassword())) {
            message = "Email or password invalid";
        }
        return message;
    }
}
