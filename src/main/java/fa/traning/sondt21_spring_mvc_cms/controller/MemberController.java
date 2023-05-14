package fa.traning.sondt21_spring_mvc_cms.controller;

import fa.traning.sondt21_spring_mvc_cms.model.dto.MemberFromDto;
import fa.traning.sondt21_spring_mvc_cms.model.dto.MemberLoginDto;
import fa.traning.sondt21_spring_mvc_cms.model.entity.Member;
import fa.traning.sondt21_spring_mvc_cms.service.MemberService;
import fa.traning.sondt21_spring_mvc_cms.service.MemberValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping
public class MemberController {
    private final MemberService memberService;
    private final MemberValidationService memberValidationService;

    public MemberController(MemberService memberService, MemberValidationService memberValidationService) {
        this.memberService = memberService;
        this.memberValidationService = memberValidationService;
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("memberFromDto", new MemberFromDto());
        return "auth/RegisterPage";
    }

    @PostMapping({"/register"})
    public String getRegister(@Valid MemberFromDto memberFromDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/RegisterPage";
        }

        if (memberService.existsEmail(memberFromDto.getEmail())) {
            bindingResult.rejectValue("email", "common.email.duplicate");
            return "auth/RegisterPage";
        }

        Member member = new Member();
        BeanUtils.copyProperties(memberFromDto, member);
        memberService.create(member);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("memberLoginDto", new MemberLoginDto());
        return "auth/LoginPage";
    }

    @PostMapping({"/login"})
    public String getLogin(@Valid MemberLoginDto memberLoginDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/LoginPage";
        }

        String errorMessage = memberValidationService.validateUser(memberLoginDto);

        if (!errorMessage.isEmpty()) {
            ObjectError objectError = new ObjectError("globalError", errorMessage);
            bindingResult.addError(objectError);
        }

        if (bindingResult.hasErrors()) {
            return "auth/LoginPage";
        }

        Member member = new Member();
        BeanUtils.copyProperties(memberLoginDto, member);

        return "redirect:/cms/view-content";
    }

    @GetMapping("/cms/edit-profile/{id}")
    public String showEditProfile(Model model, @PathVariable Long id) {
        Optional<Member> memberOptional = memberService.findById(id);
        MemberFromDto memberFromDto = new MemberFromDto();
        BeanUtils.copyProperties(memberOptional.get(), memberFromDto);

        model.addAttribute("memberFromDto", memberFromDto);
        return "content/EditProfile";
    }

    @PostMapping("/cms/edit-profile/{id}")
    public String getEditProfile(@Valid MemberFromDto memberFromDto,
                                 BindingResult bindingResult,
                                 @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "content/EditProfile";
        }

        Member member = new Member();
        BeanUtils.copyProperties(memberFromDto, member);
        member.setId(id);
        memberService.update(member);

        return "redirect:/cms/view-content";

    }
}
