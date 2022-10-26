package com.smile.blog.services;

import com.smile.blog.models.Tag;
import com.smile.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class TagService {

    private static TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public static   Iterable<Tag> getAllTag()
    {
        return tagRepository.findAll();
    }

    public static Tag findTagById(Long id){
        if(!tagRepository.existsById(id)){
            return null;
        }
        Optional<Tag> tags = tagRepository.findById(id);
        ArrayList<Tag> res = new ArrayList<>();
        tags.ifPresent(res::add);
        return res.get(0);
    }

    public static Tag findTagByName(String tag){
        return tagRepository.findByName(tag);
    }

    public static void addTag(String name, String shortDescription){
        tagRepository.save(new Tag(name,shortDescription));
    }
}
