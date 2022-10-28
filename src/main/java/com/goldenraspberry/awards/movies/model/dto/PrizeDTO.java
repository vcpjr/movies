package com.goldenraspberry.awards.movies.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class PrizeDTO {

    private List<WinnerDTO> min;
    private List<WinnerDTO> max;
}
