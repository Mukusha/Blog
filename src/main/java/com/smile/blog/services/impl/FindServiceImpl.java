package com.smile.blog.services.impl;

import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import com.smile.blog.repositories.AuthorRepository;
import com.smile.blog.repositories.PostRepository;
import com.smile.blog.repositories.TagRepository;
import com.smile.blog.services.FindService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindServiceImpl implements FindService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;

    public FindServiceImpl(PostRepository postRepository, AuthorRepository authorRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Post> findKey( String key){
        List<Post> postsFind;
        postsFind = postRepository.findBySubjectPostContainingIgnoreCaseOrAnonsPostContainingIgnoreCaseOrFullTextPostContainingIgnoreCaseOrAuthorNicknameContainingIgnoreCase(key,key,key,key);
        return postsFind;
    }

    @Override
    public List<Post> findKeys(String subjectPost,  String anonsPost, String fullTextPost, String author){
        List<Post> postsFind;
        postsFind = postRepository.findBySubjectPostContainingIgnoreCaseAndAnonsPostContainingIgnoreCaseAndFullTextPostContainingIgnoreCaseAndAuthorNicknameContainingIgnoreCase(subjectPost,anonsPost,fullTextPost,author);
        if(postsFind.size()==0) return null;
        return  postsFind;}

    @Override
    public List<Author> findAuthor(String nickname, String shortInformation){
        List<Author> authorsFind = authorRepository.findByNicknameContainingIgnoreCaseAndShortInformationContainingIgnoreCase(nickname,shortInformation);
        if(authorsFind.size()==0) return null;
        return authorsFind;
    }

    @Override
    public List<Tag> findTag(String name, String shortDescription) {
        List<Tag> tagsFind = tagRepository.findByNameContainingIgnoreCaseAndShortDescriptionContainingIgnoreCase(name,shortDescription);
        if(tagsFind.size()==0) return null;
        return tagsFind;
    }
}
