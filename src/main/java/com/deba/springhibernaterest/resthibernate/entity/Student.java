package com.deba.springhibernaterest.resthibernate.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Student {

    @Id
    private int id;
    private String name;
}
