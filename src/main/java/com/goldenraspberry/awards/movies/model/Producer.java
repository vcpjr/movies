package com.goldenraspberry.awards.movies.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="producer")
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Producer implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @ManyToMany(cascade=CascadeType.PERSIST)
    private List<Movie> movies;

    public static List<Producer> fromCSV(String[] producersNames) {
        List<Producer> producers = new ArrayList<>();
        if(producersNames != null && producersNames.length > 0){
            for (int i = 0; i < producersNames.length; i++){
                Producer p = new Producer();
                p.setName(producersNames[i].trim());

                producers.add(p);
            }
        }

        return  producers;
    }
}
