package com.goldenraspberry.awards.movies.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="movie")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer releaseYear;

    @Column
    private String title;

    //TODO criar many to many
    @Column
    private String studio;

    @JoinTable(name = "movie_producer",
            joinColumns = { @JoinColumn(name = "id_movie"), },
            inverseJoinColumns = { @JoinColumn(name = "id_producer") })
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Producer> producers;

    @Column
    private Boolean winner;

    public static Movie fromCSV(String csv){
        Movie m = new Movie();

        String[] columns = csv.split(";");

        m.setReleaseYear(Integer.valueOf(columns[0]));
        m.setTitle(columns[1]);
        m.setStudio(columns[2]);

        List<Producer> producers = new ArrayList<>();

        columns[3] = columns[3].replace("and", ",");
        String[] producersNames = columns[3].split(",");
        m.setProducers(Producer.fromCSV(producersNames));

        if(columns.length == 5){
            m.setWinner(columns[4].equals("yes"));
        }

        return m;
    }
}


