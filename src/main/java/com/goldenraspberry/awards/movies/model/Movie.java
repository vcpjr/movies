package com.goldenraspberry.awards.movies.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="movie")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer releaseYear;

    @Column
    private String title;

    @Column
    private String studio;

    @Column
    private String producer;

    @Column
    private Boolean winner;
}


