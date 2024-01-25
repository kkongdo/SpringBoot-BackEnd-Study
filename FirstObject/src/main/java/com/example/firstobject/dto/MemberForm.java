package com.example.firstobject.dto;


import com.example.firstobject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class MemberForm {
    private String email;
    private int password;
    public Member toEntity() {
        return new Member(null, email, password);
    }
}
