package com.smile.blog.services.impl;

import com.smile.blog.models.Author;
import com.smile.blog.models.Role;
import com.smile.blog.models.User;
import com.smile.blog.repositories.UserRepository;
import com.smile.blog.services.AuthorService;
import com.smile.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorService authorService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorService authorService) {
        this.userRepository = userRepository;
        this.authorService = authorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
      return userRepository.findByUsername(username);
    }

    @Override
    public List<? extends GrantedAuthority> mapRolesToAthorities(Set<Role> roles)
    {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }

    @Override
    public void addUser(User user) throws Exception
    {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null)
        {
            throw new Exception("user exist");
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        Author author= authorService.profileAdd(user.getUsername());
        user.setAuthor(author);
        userRepository.save(user);
    }

    @Override
    public Long findAuthorIdByUsername(String name){
        return userRepository.findByUsername(name).getAuthor().getId();
    }

    @Override
    public Set<Role> getRolesAuthor(long id) {
        return userRepository.findByAuthorId(id).getRoles();
    }

}

