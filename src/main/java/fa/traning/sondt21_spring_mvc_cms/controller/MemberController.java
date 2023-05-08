package fa.traning.sondt21_spring_mvc_cms.controller;

import fa.traning.sondt21_spring_mvc_cms.model.dto.MemberFromDto;
import fa.traning.sondt21_spring_mvc_cms.model.entity.Member;
import fa.traning.sondt21_spring_mvc_cms.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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

        return "redirect:/cms/edit-profile";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("memberFromDto", new MemberFromDto());
        return "auth/LoginPage";
    }

    @GetMapping("/cms/edit-profile")
    public String showEditProfile(Model model) {
        model.addAttribute("memberFromDto", new MemberFromDto());
        return "content/EditProfile";
    }
}
