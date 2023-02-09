package com.smile.blog.services;

import com.smile.blog.models.Role;
import com.smile.blog.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Добавляет нового пользователя
     * */
    void addUser(User user) throws Exception;

    /*
    * Поиск id текущего автора
    * */
    Long findAuthorIdByUsername(String name);

    Iterable<User> getAllUsers();

    void DeleteUser(Long userId);

    Set<Role> getRolesAuthor(long id);

    List<Boolean> getDisabletList(long index);

    void editRoleUser(Long userId, Boolean admin, Boolean user) throws Exception;

    /**
     * Активация аккаунта при помощи почты
     * */
    boolean activateUser(String code);

    /**
     * Отправить сообщение пользователю
     * * */
   void addEmail(User user, String email);

    String findEmailByAuthorId(Long id);

    boolean isAccountActivatedByAuthorId(Long id);
}
