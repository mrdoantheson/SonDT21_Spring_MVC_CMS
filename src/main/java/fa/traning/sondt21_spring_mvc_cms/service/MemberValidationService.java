package fa.traning.sondt21_spring_mvc_cms.service;

import fa.traning.sondt21_spring_mvc_cms.model.dto.MemberLoginDto;

public interface MemberValidationService {

    public String validateUser(MemberLoginDto memberLoginDto);
}
