package com.hh99_crewtalk.crewtalk_backend.service;

import com.hh99_crewtalk.crewtalk_backend.config.auth.PrincipalDetails;
import com.hh99_crewtalk.crewtalk_backend.domain.Article;
import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.dto.ArticleRequestDto;
import com.hh99_crewtalk.crewtalk_backend.dto.ArticleUpdateRequestDto;
import com.hh99_crewtalk.crewtalk_backend.dto.UserArticleRequestDto;
import com.hh99_crewtalk.crewtalk_backend.repository.ArticleRepository;
import com.hh99_crewtalk.crewtalk_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;


    //게시물 전체 조회 + 페이징
    @Transactional
    public List<Article>  findAllPageArticle(int page){
        Page<Article> pageArticles = articleRepository.findAll(PageRequest.of(page-1, 5, Sort.Direction.DESC, "modifiedAt"));
        List<Article> articles =  pageArticles.getContent();
        return articles;
    }

    //게시물 전체 조회 - 최신순
    @Transactional
    public List<Article> findAllArticle() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }

    //게시물 - 가장 최신 게시물만 조회
    @Transactional
    public List<Article> findRecentArticle() {
        return articleRepository.findTopByOrderByModifiedAtDesc();
    }

    //ArticleId 별 조회 -> 단일 건 조회
    @Transactional
    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    //스택별 게시글 조회 + 페이징
    @Transactional
    public List<Article> findAllArticleByStack(int page, String stack){
        Page<Article> pageArticleByStack = articleRepository.findAllByStack(stack, PageRequest.of(page-1, 5, Sort.Direction.DESC, "modifiedAt"));
        List<Article> articleByStack =  pageArticleByStack.getContent();
        return articleByStack;
    }

    //AuthorId별 게시글 조회 + 페이징
    @Transactional
    public List<Article> findAuthorIdArticle(int page, String authorId){
        Page<Article> pageArticleByAuthorId = articleRepository.findAllByAuthorId(authorId, PageRequest.of(page-1, 5, Sort.Direction.DESC, "modifiedAt"));
        List<Article> articleByAuthorId =  pageArticleByAuthorId.getContent();
        return articleByAuthorId;
    }

    // 검색어가 포함된 이름을 갖는 게시물 조회 + 페이징
    @Transactional
    public List<Article> findByArticleAuthorNameContaining(String authorName, int page){
        Page<Article> pageArticleByAuthorName = articleRepository.findByAuthorNameContaining(authorName, PageRequest.of(page-1, 5, Sort.Direction.DESC, "modifiedAt"));
        List<Article> articleByAuthorName = pageArticleByAuthorName.getContent();
        return articleByAuthorName;
    }

    //내 게시물 조회 + 페이징
    public List<Article> findAllAuthorId(Authentication authentication, int page){
        // 헤더에서 넘겨받은 토큰을 이용해 로그인한 유저정보를 찾는다.
        PrincipalDetails principalDetails = null;
        try {
            principalDetails = (PrincipalDetails) authentication.getPrincipal();
        } catch (Exception e) {
            throw new NullPointerException("토큰 정보가 없습니다");
        }

        String cur_username = principalDetails.getUsername();
        Page<Article> pageArticleByAuthorId = articleRepository.findAllByAuthorId(cur_username, PageRequest.of(page-1, 5, Sort.Direction.DESC, "modifiedAt"));
        List<Article> articleByAuthorId =  pageArticleByAuthorId.getContent();
        return articleByAuthorId;
    }


    //게시물 작성
    @Transactional
    public Article createArticle(Authentication authentication, UserArticleRequestDto userArticleRequestDto) {
        // 헤더에서 넘겨받은 토큰을 이용해 로그인한 유저정보를 찾는다.
        PrincipalDetails principalDetails = null;
        try {
            principalDetails = (PrincipalDetails) authentication.getPrincipal();
        } catch (Exception e) {
            throw new NullPointerException("토큰 정보가 없습니다");
        }

        // 그 중에서도 primary Key인 id값
        Long user_id = principalDetails.getUser().getId();
        System.out.println("id:" + user_id); // 잘 뽑아졌는지 확인용
        // id값을 이용해 user 객체를 찾아준다.
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new IllegalArgumentException("No") // 에러 대비
        );

        // 최종적으로 save에 이용할 DTO인 articleRequestDto에 값들을 셋팅해줌.
        String authorId = user.getUsername();
        String authorName = user.getName();
        String stack = user.getStack();
        String title = userArticleRequestDto.getTitle();
        String contents = userArticleRequestDto.getContents();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setAuthorId(authorId);
        articleRequestDto.setAuthorName(authorName);
        articleRequestDto.setStack(stack);
        articleRequestDto.setTitle(title);
        articleRequestDto.setContents(contents);

        Article article = new Article(articleRequestDto);
        return articleRepository.save(article);
    }

    //게시물 수정
    @Transactional
    public Long updateArticle(Authentication authentication, Long id, ArticleUpdateRequestDto articleUpdateRequestDto) {
        // 헤더에서 넘겨받은 토큰을 이용해 로그인한 유저정보를 찾는다.
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        // 그 중에서도 primary Key인 id값
        Long user_id = principalDetails.getUser().getId();
        System.out.println("user_id:" + user_id); // 잘 뽑아졌는지 확인용

        // id값을 이용해 user 객체를 찾아준다.
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new IllegalArgumentException("No") // 에러 대비
        );
        String cur_username = user.getUsername(); // 현재 접속한 user의 username
        // username 은 회원가입 당시 중복체크를 하기때문에 중복하지 않다.
        // username 을 기준으로 작성자 동일 여부를 가려도 된다는 말.

        // URI통해서 넘겨 받은 게시물의 id값을 통해 현재 수정하고자 하는 게시물 객체를 찾아줌.
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        // 해당 게시물의 작성자를 파악
        String authorId = article.getAuthorId();

        System.out.println("author:" + authorId); // 잘 뽑아졌는지 확인용
        System.out.println("cur_username:" + cur_username); // 잘 뽑아졌는지 확인용

        // 현재 유저와 작성자가 같다면!
        if (!cur_username.equals(authorId)) {
            System.out.println("자신의 게시물만 수정 할 수 있습니다!");
            throw new IllegalArgumentException("자신의 게시물만 수정 할 수 있습니다!");
        }
        article.updateArticle(articleUpdateRequestDto);
        return article.getId();
    }

    //게시물 삭제
    @Transactional
    public Long deleteArticle(Authentication authentication, Long id) {
        // 헤더에서 넘겨받은 토큰을 이용해 로그인한 유저정보를 찾는다.
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        // 그 중에서도 primary Key인 id값
        Long user_id = principalDetails.getUser().getId();
        System.out.println("user_id:" + user_id); // 잘 뽑아졌는지 확인용

        // id값을 이용해 user 객체를 찾아준다.
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new IllegalArgumentException("No") // 에러 대비
        );
        String cur_username = user.getUsername(); // 현재 접속한 user의 username
        // username 은 회원가입 당시 중복체크를 하기때문에 중복하지 않다.
        // username 을 기준으로 작성자 동일 여부를 가려도 된다는 말.

        // URI통해서 넘겨 받은 게시물의 id값을 통해 현재 수정하고자 하는 게시물 객체를 찾아줌.
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        // 해당 게시물의 작성자를 파악
        String authorId = article.getAuthorId();

        System.out.println("author:" + authorId); // 잘 뽑아졌는지 확인용
        System.out.println("cur_username:" + cur_username); // 잘 뽑아졌는지 확인용

        // 현재 유저와 작성자가 같다면!
        if (!cur_username.equals(authorId)) {
            System.out.println("자신의 게시물만 삭제 할 수 있습니다!");
            throw new IllegalArgumentException("자신의 게시물만 삭제 할 수 있습니다!");
        }
        articleRepository.deleteById(id);
        return id;
    }



}
