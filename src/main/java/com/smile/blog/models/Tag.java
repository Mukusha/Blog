package com.smile.blog.models;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String shortDescription;

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    public Tag(String name, String shortDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
    }
}
