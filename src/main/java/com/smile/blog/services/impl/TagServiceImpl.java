package com.smile.blog.services.impl;

import com.smile.blog.models.Tag;
import com.smile.blog.repositories.TagRepository;
import com.smile.blog.services.TagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
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

    public void addTag(String name, String shortDescription){
        tagRepository.save(new Tag(name,shortDescription));
    }
}
