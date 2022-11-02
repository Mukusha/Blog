package com.smile.blog.services;

import com.smile.blog.models.Tag;

public interface TagService {

    Iterable<Tag> getAllTag();

    Tag findTagById(Long id);

     Tag findTagByName(String tag);

    void addTag(String name, String shortDescription);
}
