package com.smile.blog.services.impl;

import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import com.smile.blog.repositories.PostRepository;
import com.smile.blog.services.AuthorService;
import com.smile.blog.services.PostService;
import com.smile.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    private Post post = new Post(null, null,null,null,null);
    private final PostRepository postRepository;
    private final AuthorService authorService;

    private final TagService tagService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, AuthorService authorService, TagService tagService) {
        this.postRepository = postRepository;
        this.authorService = authorService;
        this.tagService = tagService;
    }

    @Override
    public  Iterable<Post> getAllPost()
    {
        return postRepository.findAll();
    }

    @Override
    public void postAdd(Post post){
        postRepository.save(post);
    }

    @Override
    public ArrayList<Post> postDetails(Long id){
        if(!postRepository.existsById(id)){
            return null;
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }

    @Override
    public void findPostById(Long id){
        Optional<Post> postNew = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        postNew.ifPresent(res::add);
        post = res.get(0);
    }

    @Override
    public Iterable<Post> getAllPostAuthor(Author author) {
        return postRepository.findByAuthor(author);
    }

    @Override
    public void blogPostUpdate( String subjectPost,  String anonsPost,  String fullTextPost, String tag){
        if (subjectPost!=null) post.setSubjectPost(subjectPost);
        if (anonsPost!=null) post.setAnonsPost(anonsPost);
        if (fullTextPost!=null) post.setFullTextPost(fullTextPost);

        Tag tagNew = tagService.findTagByName(tag);
        if (tagNew!=null)    {
            Set<Tag> tagsN= post.getTags();
            if(tagsN == null) tagsN = new HashSet<>();
            tagsN.add(tagNew);
            post.setTags(tagsN);
        }
    }

    @Override
    public void postRemoveById(Long id){
        Post post=postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }

    @Override
    public void saveAndResetPost(){
        postAdd( post);
        post=new Post(null, null,null,null,null);
    }

    @Override
    public Post getPost() {
        return post;
    }
}
