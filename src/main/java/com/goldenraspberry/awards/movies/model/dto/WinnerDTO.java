package com.goldenraspberry.awards.movies.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinnerDTO {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
