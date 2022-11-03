package com.smile.blog.services;
import com.smile.blog.models.Role;
import com.smile.blog.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

     List<? extends GrantedAuthority> mapRolesToAthorities(Set<Role> roles);

    void addUser(User user) throws Exception;
}
