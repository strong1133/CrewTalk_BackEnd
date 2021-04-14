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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;


    //게시물 전체 조회 - 최신순
    @Transactional
    public List<Article> findAllArticle() {
        return articleRepository.findAllByOrderByModifiedAt();
    }

    @Transactional
    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }


    //게시물 작성
    @Transactional
    public Article createArticle(Authentication authentication, UserArticleRequestDto userArticleRequestDto) {
        // 헤더에서 넘겨받은 토큰을 이용해 로그인한 유저정보를 찾는다.
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        // 그 중에서도 primary Key인 id값
        Long user_id = principalDetails.getUser().getId();
        System.out.println("id:" + user_id); // 잘 뽑아졌는지 확인용
        // id값을 이용해 user 객체를 찾아준다.
        User user = userRepository.findById(user_id).orElseThrow(
                () -> new IllegalArgumentException("No") // 에러 대비
        );

        // 최종적으로 save에 이용할 DTO인 articleRequestDto에 값들을 셋팅해줌.
        String ahtuor = user.getUsername();
        String stack = user.getStack();
        String title = userArticleRequestDto.getTitle();
        String contents = userArticleRequestDto.getContents();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setAuthor(ahtuor);
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
        String author = article.getAuthor();

        System.out.println("author:" + author); // 잘 뽑아졌는지 확인용
        System.out.println("cur_username:" + cur_username); // 잘 뽑아졌는지 확인용

        // 현재 유저와 작성자가 같다면!
        if (!cur_username.equals(author)) {
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
        String author = article.getAuthor();

        System.out.println("author:" + author); // 잘 뽑아졌는지 확인용
        System.out.println("cur_username:" + cur_username); // 잘 뽑아졌는지 확인용

        // 현재 유저와 작성자가 같다면!
        if (!cur_username.equals(author)) {
            System.out.println("자신의 게시물만 삭제 할 수 있습니다!");
            throw new IllegalArgumentException("자신의 게시물만 삭제 할 수 있습니다!");
        }
        articleRepository.deleteById(id);
        return id;
    }


}
