package com.goldenraspberry.awards.movies.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    public static Set<Producer> fromCSV(String[] producersNames) {
        Set<Producer> producers = new HashSet<>();
        if(producersNames != null && producersNames.length > 0){
            for (int i = 0; i < producersNames.length; i++){
                if(!producersNames[i].trim().isEmpty()){
                    Producer p = new Producer();
                    p.setName(producersNames[i].trim());

                    producers.add(p);
                }
            }
        }

        return  producers;
    }
}
