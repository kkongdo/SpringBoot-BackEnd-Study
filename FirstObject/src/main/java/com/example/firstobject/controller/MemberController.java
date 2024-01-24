package com.example.firstobject.controller;

import com.example.firstobject.dto.MemberForm;
import com.example.firstobject.entity.Member;
import com.example.firstobject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String newSingn(){
        return "members/new";
    }
    @PostMapping("/join")
    public String join(MemberForm form){
        Member member = form.toEntity();
        memberRepository.save(member);
        return"";
    }
}
