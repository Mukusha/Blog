package com.smile.blog.repositories;

import com.smile.blog.models.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag,Long> {
   Tag findByName(String name);
   boolean existsByName(String name);
   List<Tag> findByNameContainingIgnoreCaseAndShortDescriptionContainingIgnoreCase(String Name, String ShortDescription);

}
