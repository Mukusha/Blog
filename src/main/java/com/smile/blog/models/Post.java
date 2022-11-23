package com.smile.blog.models;



import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
   // @JoinColumn(name = "user_id")
    private Author author;
    private String subjectPost;
    private String anonsPost;
    private String fullTextPost;

    @ManyToMany
    private Set<Tag> tags;

    public Post() {}
    public Post(Author author, String subjectPost, String anonsPost, String fullTextPost, Set<Tag> tags) {
        this.author = author;
        this.subjectPost = subjectPost;
        this.anonsPost = anonsPost;
        this.fullTextPost = fullTextPost;
        this.tags = tags;
    }

}
