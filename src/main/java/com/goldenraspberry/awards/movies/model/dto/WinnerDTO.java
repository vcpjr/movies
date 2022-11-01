package com.goldenraspberry.awards.movies.model.dto;

import lombok.*;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WinnerDTO {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    public static List<WinnerDTO> fromTuple(List<Tuple> maxWinners) {
        List<WinnerDTO> winnersDTO = maxWinners.stream()
                .map(t -> new WinnerDTO(
                        t.get(0, String.class),
                        t.get(1, Integer.class),
                        t.get(2, Integer.class),
                        t.get(3, Integer.class)
                ))
                .collect(Collectors.toList());

        return winnersDTO;
    }
}
