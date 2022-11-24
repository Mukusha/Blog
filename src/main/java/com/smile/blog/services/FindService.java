package com.smile.blog.services;

import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FindService {
    /**
    * Поиск заданного ключа
    * @return Возвращает посты в которых данный ключ был найден в Теме, анонсе, тексте или авторе
    * */
    List<Post> findKey(String key);
    /**
     * Поиск публикации по заданным параметрам
     * @param subjectPost - тема публикации
     * @param anonsPost - анонс публикации
     * @param fullTextPost - текст публикации
     * @param author - автор публикации
     * @return Возвращает посты в которых данный ключ был найден в теме, анонсе, тексте, авторе
     * */
    List<Post> findKeys( String subjectPost,  String anonsPost, String fullTextPost, String author);

    /**
     * Поиск заданного ключа в списке авторов
     * @return Возвращает посты в которых данный ключ был найден в Теме, анонсе, тексте, авторе
     * */
    List<Author> findAuthor(String nickname, String shortInformation);

    /**
     * Поиск заданного ключа в списке тегов
     * @return Возвращает теги в которых данный ключ был найден
     * */
    List<Tag> findTag(String name, String shortDescription);
}
