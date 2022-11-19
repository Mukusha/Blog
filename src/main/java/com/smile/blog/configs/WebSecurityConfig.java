package com.smile.blog.configs;

import com.smile.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    //сконфигурировать доступность путей разным ролям, например так
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //Доступ разрешен всем пользователей
                .antMatchers("/login", "/registration").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                //что бы не падали Post запросы. Это связано с токенами
                .and() .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().formLogin().permitAll()
                .and().logout().permitAll();
        //   return http.build();
    }


    // сохранение информации о пользователе, что бы получать её в контроллере
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
