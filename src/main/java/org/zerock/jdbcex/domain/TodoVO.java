package org.zerock.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

//lombok 사용
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class TodoVO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}

