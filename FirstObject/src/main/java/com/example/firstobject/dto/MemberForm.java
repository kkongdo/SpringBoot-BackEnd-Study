package com.example.firstobject.dto;


import com.example.firstobject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private Long password;
    public Member toEntity() {
        return new Member(id, email, password);
    }
}
