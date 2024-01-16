package org.zerock.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

//lombok 사용
@Builder
@Getter //vo를 통해 자료를 끄집어냄
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class TodoVO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}

