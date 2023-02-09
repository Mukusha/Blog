package com.smile.blog.services.impl;

import com.smile.blog.models.Author;
import com.smile.blog.models.Role;
import com.smile.blog.models.User;
import com.smile.blog.repositories.UserRepository;
import com.smile.blog.services.AuthorService;
import com.smile.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;


@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthorService authorService;
    private final MailService mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorService authorService, MailService mailSender) {
        this.userRepository = userRepository;
        this.authorService = authorService;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addUser(User user) throws Exception {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            throw new Exception("user exist");
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);

        Author author = authorService.profileAdd(user.getUsername());
        user.setAuthor(author);
        userRepository.save(user);
    }

    public void addEmail(User user, String email){
        user.setEmail(email);
        user.setActivationCode(UUID.randomUUID().toString()); //не активированная учетная запись
        userRepository.save(user);
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Привет, %s! \n" +
                            "Доброе пожаловать в Blog. Пожалуйста, перейдите по следующей ссылке: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Код активации", message);
        }
    }


    @Override
    public Long findAuthorIdByUsername(String name) {
        return userRepository.findByUsername(name).getAuthor().getId();
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void DeleteUser(Long userId) {
        var maybeUser = userRepository.findById(userId);
        if (maybeUser.isEmpty()) return;
        var user = maybeUser.get();
        userRepository.delete(user);
    }

    public Set<Role> getRolesAuthor(long id) {
        return userRepository.findByAuthorId(id).getRoles();
    }

    public List<Boolean> getDisabletList(long index){
        List<Boolean> disabletUserList= new ArrayList<>();
        Iterable<User> listUser = userRepository.findAll();
        for (User user:listUser) {
            if(user.getId() == index && index!=-1) disabletUserList.add(false);
            else disabletUserList.add(true);
        }
      //  System.out.println(disabletUserList);
        return disabletUserList;
    }

    public void editRoleUser(Long userId, Boolean admin, Boolean user) {
        if(!userRepository.existsById(userId)){
           // throw new Exception("user does not exist");
            System.out.println("Пользователя не существует!!!");
            return;
        }

        Set<Role> roles = new HashSet<>();
        if (admin != null && user !=null) { roles.add(Role.USER); roles.add(Role.ADMIN);}
        if (admin != null && user ==null) roles.add(Role.ADMIN);
        if (admin == null && user !=null) roles.add(Role.USER);
        if (admin == null && user ==null) { System.out.println("Пользователь не может быть без ролей!!!"); return;}
        Optional<User> users = userRepository.findById(userId);
        User userNew = users.get();
        userNew.setRoles(roles);
        userRepository.save(userNew);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null); // помечаем, что аккаунт активирован
        userRepository.save(user);

        return true;
    }

    public String findEmailByAuthorId(Long id){
        User user = userRepository.findByAuthorId(id);
        if (user.getEmail() == null) return "нет привязанной почты";
        return user.getEmail();
    }

    public boolean isAccountActivatedByAuthorId(Long id){
        User user = userRepository.findByAuthorId(id);
        if (user.getEmail() != null & user.getActivationCode()==null) return true;
        return false;
    }
}

