package com.example.firstobject.repository;

import com.example.firstobject.entity.Article;
import com.example.firstobject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // CASE 1 : 4번게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는 ? ", "댓글부탁");
            Comment a = new Comment(1L, article, "PARK", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "KIM", "아이 엠 샘");
            Comment c = new Comment(3L, article, "CHOI", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(comments.toString(), expected.toString());
        }
        // CASE 2 : 1번게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(comments.toString(), expected.toString(), "1번 글은 댓글이 없음");
        }
        // CASE 3 : 9번 게시글의 모든 댓글 조회
        {
            //1.입력데이터준비
            Long articleId = 9L;
            //2.실제데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //3.예상데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            //4.비교 및 검증
            assertEquals(comments.toString(), expected.toString(),"9번 게시글은 없음");
        }
        // CASE 4: 999번 게시글의 모든 댓글 조회
        {
            //1.입력데이터준비
            Long articleId = 999L;
            //2.실제데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //3.예상데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            //4.비교 및 검증
            assertEquals(comments.toString(), expected.toString(), "999번 게시글이 없으므로 댓글 존재못함");
        }
        // CASE 5: -1번 게시글의 모든 댓글 조회
        {
            //1.입력데이터준비
            Long articleId = -1L;
            //2.실제데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //3.예상데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();
            //4.비교 및 검증
            assertEquals(comments.toString(), expected.toString(), "-1번 게시글이 없으므로 댓글 존재못함");
        }
    }

    @Test
    @DisplayName("특정닉네임의모든댓글조회")
    void findByNickname() {
        //Case 1 : "PARK"의 모든 댓글 조회
        {
            // 1.입력 데이터 준비
            String nickname = "PARK";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상 데이터
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는 ? ", "댓글부탁"), nickname, "굿 윌 헌팅");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는 ? ", "댓글부탁"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는 ?", "댓글부탁"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "PARK의 모든 댓글을 출력!");

        }
        //CASE 2 : "KIM"의 모든 댓글 조회
        {
            //1.입력데이터 준비
            String nickname = "KIM";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상데이터
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는 ? ", "댓글부탁"), nickname, "아이 엠 샘");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는 ? ", "댓글부탁"), nickname, "샤브샤브");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는 ?", "댓글부탁"), nickname, "유튜브 시청");
            List<Comment> expected = Arrays.asList(a, b ,c);
            //4. 비교 및 검증
            assertEquals(comments.toString(), expected.toString(), "KIM의 모든 댓글을 출력!");
        }
        //CASE 3 : null의 모든 댓글 조회
        {
            //1.입력데이터 준비
            String nickname = null;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 3. 예상데이터
            List<Comment> expected = Arrays.asList();
            //4. 비교 및 검증
            assertEquals(comments, expected, "null의 모든 댓글을 출력!");
        }
        //1.입력데이터 준비
        String nickname = "";
        // 2. 실제 데이터
        List<Comment> comments = commentRepository.findByNickname(nickname);
        // 3. 예상데이터
        List<Comment> expected = Arrays.asList();
        //4. 비교 및 검증
        assertEquals(comments, expected, " \"\"의 모든 댓글을 출력! ");
    }
}