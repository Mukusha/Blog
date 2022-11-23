package com.smile.blog.services.impl;

import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import com.smile.blog.repositories.PostRepository;
import com.smile.blog.repositories.TagRepository;
import com.smile.blog.services.TagService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    private List<Tag> tags = new ArrayList<>();
    public TagServiceImpl(TagRepository tagRepository, PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    public  Iterable<Tag> getAllTag()
    {
        return tagRepository.findAll();
    }

    public Tag findTagById(Long id){
        if(!tagRepository.existsById(id)){
            return null;
        }
        Optional<Tag> tags = tagRepository.findById(id);
        ArrayList<Tag> res = new ArrayList<>();
        tags.ifPresent(res::add);
        return res.get(0);
    }

    public Tag findTagByName(String tag){
        if(!tagRepository.existsByName(tag)){
            return null;
        }
        return tagRepository.findByName(tag);
    }

    public List<Post> postFindByTagId(Long id){
        Tag tag = tagRepository.findById(id).get();
        return postRepository.findByTags(tag);
    }

    public void addTag(String name, String shortDescription){
        tagRepository.save(new Tag(name,shortDescription));
    }

    public List<Tag> findAnalogTagByName(String name){
        List<Tag> tags = tagRepository.findByNameContainingIgnoreCase(name);
        return tags;
    }

    public List<Tag> getTags(){
        return tags;
    }

    public List<Tag> setTags(List<Tag> tags){
        return this.tags=tags;
    }

    public void deleteLocTag(Tag tag){
         this.tags.remove(tag);
    }
}
