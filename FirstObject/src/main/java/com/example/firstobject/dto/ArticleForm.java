package com.example.firstobject.dto;

import com.example.firstobject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ArticleForm {
    private String title;
    private String content;
    public Article toEntity() {
        return new Article(null, title, content);
    }
}
