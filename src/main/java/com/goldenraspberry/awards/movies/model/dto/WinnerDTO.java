package com.goldenraspberry.awards.movies.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WinnerDTO {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
}
