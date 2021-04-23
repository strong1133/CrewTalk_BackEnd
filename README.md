</br>

<h1 align="center" > 항해99 미니프로젝트 Crew_Talk :zap: </h1>

</br></br>

</br>

# Crew_Talk

💻 프론트 & 백 : React Native + Spring

😃 <a href="https://strong1133.tistory.com/7">개발 블로그</a>

📺 <a href="https://www.youtube.com/watch?v=x1XWfufVxCQ">유튜브</a>

📁 <a href="https://github.com/delilah1004">프론트 앤드 Git Hub</a>

📁 <a href="https://github.com/strong1133/CrewTalk_BackEnd">백 앤드 Git Hub</a>

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

- 스프링 기반의 어플리케이션의 보안(인증과 인가)를 담당하는 프레임워크이다.
- 만약, 스프링 시큐리티를 사용하지 않는다면 자체적으로 세셴을 체크하고 redirect을 수행 해줘야함.
- 스프링 시큐리티는 filter 기반으로 동작한다.

</br>

> **JWT**

<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUlcQJem6nPUpJZzzKDIn4jwpM_NX8X4xdtg&usqp=CAU" width="20%"></img>

      JWT 토근을 구현하는데 참고한 유튜브 영상: https://www.youtube.com/watch?v=GEv_hw0VOxE&list=PL93mKxaRDidERCyMaobSLkvSPzYtIk0Ah

</p>

</br>

- 토큰 방식의 인증 에서 사용

- 서버 인증 방식의 문제

      1. 유저의 인증 기록을 서버 세션에 저장 -> 동접자가 많으면 서버에 부하가 커짐.
      2. 개발 규모가 커져서 서버가 증설 되면 1서버에서 인증된 세션이 2서버에 연동이 되지 않는 문제 발생
      3. CORS 관련 오류

- 토큰 인증 방식

      1. 유저의 인증 기록을 토큰에 담아 클라이어트에게 전달 -> 서버가 기록하지 않음!
      2. 클라이언트는 쿠키에 토큰을 담아두고 있다가 인가가 필요한 경우 요청시 토큰을 같이 전달
      3. 서버는 토큰이 유호한지 아닌지만 판단해주면 됨.

</br>

> **JPA**

<img src="https://cdn.inflearn.com/public/courses/324109/course_cover/161476f8-f0b7-4b04-b293-ce648c2ea445/kyh_jsp.png" width="25%"></img>

      JPA는 개발자를 정말 편하게 해준다.
      각종 쿼리들을 JPA를 통해 처리 해줬으며 Specification을 통한 다중 조건문 또한 구현이 가능 했다.

</p>

</br>

> 장점

- 생산성

      개발자가 일일히 CRUD용 쿼리를 작성해야 한다면 객체 중심 개발이 아닌
      DB중심의 개발을 하게됨.

- 유지보수

      쿼리 수정이 필요한 경우 JPA 사용시 단순히 엔티티 클래스의 정보만 변경해주면 된다.

- DB종류와 무관한 코딩

      DB종류마다 쿼리의 형태가 다른데 JPA를 쓰면 문법을 일일히 맞춰 줘야하는 수고를 덜 수 있다.

</br>

> 단점

- JPA는 실시간 처리용 쿼리에 최적화 되어 있다.

- 미세하고 정교한 쿼리 작업이 필요한 경우는 JPA말고 다른 방식을 사용하는게 더 효율적일 수 있다.

</br>

<hr/>

</br>
</br>

## 👑 세부 기술에 대한 고찰

> ## 정석진의 TIL: https://github.com/strong1133/TIL/tree/main/spring

  </br>
