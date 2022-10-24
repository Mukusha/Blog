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
    private String fioAuthor;
    private String nickname;
    private String shortInformation;
    private Timestamp dateOfBirth;
    private Timestamp dateCreate;

    public Author() {}

    public Author(String fioAuthor, String nickname, String shortInformation, Timestamp dateOfBirth) {
        this.fioAuthor = fioAuthor;
        this.nickname = nickname;
        this.shortInformation = shortInformation;
        this.dateOfBirth = dateOfBirth;
       // this.dateCreate = <текущая дата и время>
    }

    public Author(String fioAuthor, String nickname, String shortInformation) {
        this.fioAuthor = fioAuthor;
        this.nickname = nickname;
        this.shortInformation = shortInformation;
        this.dateOfBirth = null;
        // this.dateCreate = <текущая дата и время>
    }
}
