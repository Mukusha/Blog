package com.smile.blog.repositories;

import com.smile.blog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
    
    User findByAuthorId(Long id);

    User findByActivationCode(String code);
}
