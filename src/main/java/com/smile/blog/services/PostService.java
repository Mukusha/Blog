package com.smile.blog.services;

import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import com.smile.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class PostService {

    private Post post = new Post(null, null,null,null,null);
    private static PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;

    }

    public  Iterable<Post> getAllPost()
    {
        return postRepository.findAll();
    }

    public void postAdd(Post post){
        //работа должна добавляться только 1 автору - текущему пользователю
        //найти по id и вернуть
        post.setAuthor(AuthorService.findAuthorById(7L));
        postRepository.save(post);
    }

    public ArrayList<Post> postDetails(Long id){
        if(!postRepository.existsById(id)){
            return null;
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }

    public void findPostById(Long id){
        Optional<Post> postNew = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        postNew.ifPresent(res::add);
        post = res.get(0);
    }

    public void blogPostUpdate(String subjectPost,  String anonsPost,  String fullTextPost, String tag){
        if (subjectPost!=null) post.setSubjectPost(subjectPost);
        if (anonsPost!=null) post.setAnonsPost(anonsPost);
        if (fullTextPost!=null) post.setFullTextPost(fullTextPost);

        Tag tagNew = TagService.findTagByName(tag);
        if (tagNew!=null)    {
            Set<Tag> tagsN= post.getTags();
            if(tagsN == null) tagsN = new HashSet<>();
            tagsN.add(tagNew);
            post.setTags(tagsN);
        }
    }
    public void postRemoveById(Long id){
        Post post=postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }

    public void saveAndResetPost(){
        postAdd( post);
        post=new Post(null, null,null,null,null);
    }

    public Post getPost() {
        return post;
    }
}
