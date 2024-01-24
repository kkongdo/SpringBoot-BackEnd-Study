package com.example.firstobject.dto;


import com.example.firstobject.entity.Member;

public class MemberForm {
    private String email;
    private int password;

    public MemberForm(String email, int password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "email='" + email + '\'' +
                ", password=" + password +
                '}';
    }

    public Member toEntity() {
        return new Member(null, email, password);
    }
}
