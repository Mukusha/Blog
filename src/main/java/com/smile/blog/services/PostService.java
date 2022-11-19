package com.smile.blog.services;

import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface PostService {

    /**
     * Возвращает список всех постов
     * */
    Iterable<Post> getAllPost();

    /**
     * Сохраняет пост в таблицу
     * */
    void postAdd(Post post);

    /**
     * Возвращает детали поста по его id
     * @return ArrayList<Post> - надо переделать, так как по идее на 1 id должен возвращаться только 1 пост
     * */
    ArrayList<Post> postDetails(Long id);

    /**
     * Ищет пост по его id и сохраняет его в локальную переменную post
     */
    void findPostById(Long id);

    /**
     * Возвращает список постов автора
     * */
    Iterable<Post> getAllPostAuthor(Author author);

    /**
     * Обновляет данные локального post
     * */
    void blogPostUpdate(String subjectPost,  String anonsPost,  String fullTextPost, String tag);
    /**
     * По id удаляет пост из таблицы
     * */
    void postRemoveById(Long id);

    /**
     * Сохраняет данные локального post в таблицу и обнуляет его
     * */
    void saveAndResetPost();

    /**
     * Сохраняет локальный post
     * */
    Post getPost();
}
