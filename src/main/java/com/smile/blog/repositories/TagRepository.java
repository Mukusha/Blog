package com.smile.blog.repositories;

import com.smile.blog.models.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag,Long> {
   Tag findByName(String name);
}
