package com.ssg.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder //빌더패턴 적용할것  TodoVO.builder().build() 로 객체 생성
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;


}
