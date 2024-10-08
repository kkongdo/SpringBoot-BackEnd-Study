package com.example.firstobject.controller;

import com.example.firstobject.dto.ArticleForm;
import com.example.firstobject.dto.CommentDto;
import com.example.firstobject.entity.Article;
import com.example.firstobject.repository.ArticleRepository;
import com.example.firstobject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString());
        log.info(form.toString());
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());
        //2. 레포지토리로 엔터티를 DB에 저장
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", article);
        model.addAttribute("commentDtos", commentsDtos);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        ArrayList<Article> list = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList",list);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        return "articles/edit";
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        log.info("삭제요청이 들어왔습니다!!");
//      1.  삭제할 대상 가져오기
        Article deleteArticle = articleRepository.findById(id).orElse(null);
        log.info(deleteArticle.toString());
        if(deleteArticle != null){
            articleRepository.delete(deleteArticle);
        }
//      2. 대상 엔터티 삭제하기
        redirectAttributes.addFlashAttribute("msg","삭제되었습니다!");
        return"redirect:/articles";
    }
    @PostMapping("/articles/update")
    public String update(Model model, ArticleForm form){
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target != null){
            articleRepository.save(articleEntity);
        }
        return"redirect:/articles/" + articleEntity.getId();
    }
}