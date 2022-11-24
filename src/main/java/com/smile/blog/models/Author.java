package com.smile.blog.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String shortInformation;
    private Timestamp dateOfBirth;
    private Timestamp dateCreate;

    public Author() {}

    public Author(String nickname, String shortInformation, Timestamp dateOfBirth) {
        this.nickname = nickname;
        this.shortInformation = shortInformation;
        this.dateOfBirth = dateOfBirth;
        this.dateCreate  = new Timestamp(System.currentTimeMillis());
    }

    public Author(String nickname) {
        this.nickname = nickname;
        this.shortInformation = "";
        this.dateCreate  = new Timestamp(System.currentTimeMillis());
    }
    
    public long getAge(){
        if (this.dateOfBirth == null) return 0L;
        return (new Timestamp(System.currentTimeMillis()).getYear() - this.dateOfBirth.getYear());
    }
}
