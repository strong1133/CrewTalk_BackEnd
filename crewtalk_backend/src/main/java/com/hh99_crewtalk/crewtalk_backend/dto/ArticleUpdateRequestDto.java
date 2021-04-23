package com.hh99_crewtalk.crewtalk_backend.dto;

import lombok.Getter;

@Getter
// 수정하고자 할때 사용 되는 DTO
// 작성한 유저 정보는 바뀔수 없는 값이기 때문에 포함 안시킴
public class ArticleUpdateRequestDto {
    private String title;
    private String contents;
}
