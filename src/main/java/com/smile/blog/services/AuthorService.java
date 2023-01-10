package com.smile.blog.services;

import com.smile.blog.models.Author;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@Component
public interface AuthorService {

    /**
     * Добавляет данные нового пользователя в таблицу
     * пока указываются не все данные, далее будет дополнено датой рождения, регистрации
     * @param nickname - ник пользователя
     * */
    Author profileAdd(String nickname);

    /**
     * Поиск информации об авторе по его id
     * */
    Author findAuthorById(Long id);

    /**
     * Поиск информации об авторе по его nicname
     * @param nickname - ник пользователя
     * */
   Long findIdByNickname(String nickname);
    /**
     * Сохранить изменения в профиле
     * @param nickname - ник пользователя
     * */
     void AuthorSaveUpdate(Long id, String nickname, String shortInformation, String dateOfBirth, MultipartFile file) throws IOException, ParseException ;

        //   void AuthorSaveUpdate(Long id, String nickname,String shortInformation,  String dateOfBirth) throws ParseException;

}
