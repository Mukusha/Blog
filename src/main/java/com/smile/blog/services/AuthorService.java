package com.smile.blog.services;

import com.smile.blog.models.Author;
import org.springframework.stereotype.Component;

@Component
public interface AuthorService {

    /**
     * Добавляет данные нового пользователя в таблицу
     * пока указываются не все данные, далее будет дополнено датой рождения, регистрации
     * @param fio - ФИО пользователя
     * @param nickname - ник пользователя
     * @param shortInformation - краткая информация о пользователе
     * */
    void profileAdd(String fio, String nickname, String shortInformation);

    /**
     * Поиск информации об авторе по его id
     * */
    Author findAuthorById(Long id);

    /**
     * Поиск информации об авторе по его nicname
     * @param nickname - ник пользователя
     * */
   Long findIdByNickname(String nickname);
}
