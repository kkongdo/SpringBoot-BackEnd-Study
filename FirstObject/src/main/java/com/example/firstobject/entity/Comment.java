package com.example.firstobject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "article_id") // 외래키 생성, Article 엔티티의 기본키와 매핑
    private Article article; // 해당 댓글의 부모 게시글
    @Column
    private String nickname;
    @Column
    private String body;

}
