package com.DemoProject.cmp.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Integer id;
    private Integer userId;
    private String title;
    private boolean completed;
}