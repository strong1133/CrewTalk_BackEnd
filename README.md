</br>

<h1 align="center" > 항해99 미니프로젝트 Crew_Talk :zap: </h1>

</br></br>

</br>

# Crew_Talk

💻 프론트 & 백 : React Native + Spring

😃 <a href="https://strong1133.tistory.com/7">개발 블로그</a>

📺 <a href="https://www.youtube.com/watch?v=x1XWfufVxCQ">유튜브</a>

📁 <a href="https://github.com/delilah1004">프론트 앤드 Git Hub</a>

📁 <a href="https://github.com/strong1133/CrewTalk_BackEnd/tree/main/img">백 앤드 Git Hub</a>

</br>

## 🤠 Part

총 3인

- 이다은: 프론트(React Native)
- 정석진: 백앤드(Spring)
- 강상연: 백앤드(Spring)

</br>

## :mag: 요약

- Crew Talk 은 클론코딩을 지나 Chapter 5 까지 영차영차 올라온 항해 크루원들을 위한 소통의 장입니다.

- 크루원들의 항해99 이모저모와, 각종 꿀 정보들을 공유해 볼 수 있고,

- 나아가 주특기별로 크루원들의 게시글을 모아서 조회해 볼 수 있는 서비스를 제공합니다.

</br>

## 🦄 프로젝트 썸네일

<p align="">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EC%8D%B8%EB%84%A4%EC%9D%BC.png?raw=true" width="70%"></img>
</p>

</br>

## :rocket:기능

- 회원가입 & 로그인
- 게시글 작성 및 조회, 댓글
- 카테고리별 유저 정보, 게시글 모아보기

</br>

## :closed_book: 기술

#### :orange_book: frontend

- React Native

#### :orange_book: backend

- Spring
- My SQL

#### :orange_book: Hosting

- Expo -> apk

</br>

## :package: 개발 환경

- VS Code
- IntelliJ

</br>

## 📸 Schreenshot

#### :heavy_check_mark: 로그인 & 회원가입

<p align="center">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EB%A1%9C%EA%B7%B8%EC%9D%B8.jpeg?raw=true" width="46%">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.jpeg?raw=true" width="46%"></img></img>
</p>

</br>

#### :heavy_check_mark: 메인페이지 & 마이페이지

<p align="center">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EB%A9%94%EC%9D%B8.jpeg?raw=true" width="46%">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EB%A7%88%EC%9D%B4%ED%8E%98%EC%9D%B4%EC%A7%80.jpeg?raw=true" width="46%"></img></img>
</p>

</br>

#### :heavy_check_mark: 게시글 작성 & 게시글 상세 조회 및 댓글 작성

<p align="center">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EA%B2%8C%EC%8B%9C%EA%B8%80%EC%9E%91%EC%84%B1.jpeg?raw=true" width="46%">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EA%B2%8C%EC%8B%9C%EA%B8%80%EC%83%81%EC%84%B8+%EB%8C%93%EA%B8%80.jpeg?raw=true" width="46%"></img></img>
</p>
</br>

#### :heavy_check_mark: 스택별 유저 조회 및 특정 유저의 정보, 작성 글 모아 보기

<p align="center">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EC%8A%A4%ED%83%9D%EB%B3%84%20%EC%9C%A0%EC%A0%80%EA%B2%80%EC%83%89.jpeg?raw=true" width="46%">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EC%9C%A0%EC%A0%80%EC%A0%95%EB%B3%B4%EB%B3%B4%EA%B8%B0+%EC%9C%A0%EC%A0%80%EC%9E%91%EC%84%B1%EA%B2%8C%EC%8B%9C%EB%AC%BC.jpeg?raw=true" width="46%"></img></img>
</p>

</br>

#### :heavy_check_mark: 스택별 게시글 모아보기

<p align="center">
<img src="https://github.com/strong1133/CrewTalk_BackEnd/blob/main/img/%EC%8A%A4%ED%83%9D%EB%B3%84%EA%B2%8C%EC%8B%9C%EA%B8%80%EC%A1%B0%ED%9A%8C.jpeg?raw=true" width="90%"></img>
</p>

</br>
</br>

## 💽 백엔드 세부 기능

<hr/>

> **스프링 시큐리티**

<img src="https://pbs.twimg.com/profile_images/1235983944463585281/AWCKLiJh.png" width="20%"></img>

</p>

      해당 프로젝트 에서는 스프링 시큐리티 필터 체인을 사용하여 서버를 구축했다.
      먼저 CORS 정책 허용을 위해서 `CorsConfiguration` 을 필터체인에 더 해주 주었고 JWT토근을 이용해서
      토큰 인증을 통한 인가 처리를 구현하였다.

</br>

> **JWT**

<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUlcQJem6nPUpJZzzKDIn4jwpM_NX8X4xdtg&usqp=CAU" width="20%"></img>

      JWT 토근을 구현하는데 참고한 유튜브 영상: https://www.youtube.com/watch?v=GEv_hw0VOxE&list=PL93mKxaRDidERCyMaobSLkvSPzYtIk0Ah

</p>

</br>

> **JPA**

<img src="https://cdn.inflearn.com/public/courses/324109/course_cover/161476f8-f0b7-4b04-b293-ce648c2ea445/kyh_jsp.png" width="25%"></img>

      JPA는 개발자를 정말 편하게 해준다.
      각종 쿼리들을 JPAf를 통해 처리 해줬으며 Specification을 통한 다중 조건문 또한 구현이 가능 했다.

</p>

</br>

## 👑 세부 기술에 대한 고찰

> ## 정석진의 TIL: https://github.com/strong1133/TIL/tree/main/spring

  </br>
