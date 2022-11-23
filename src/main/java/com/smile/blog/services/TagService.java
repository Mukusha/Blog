package com.smile.blog.services;

import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;

import java.util.List;

public interface TagService {
    /**
     * Возвращает все теги из таблицы
     * */
    Iterable<Tag> getAllTag();
    /**
     * Возвращает Tag по его id
     * @return Tag - если тег найден и null - если нет
     * */
    Tag findTagById(Long id);

    /**
     * Возвращает тег по его Имени
     * @param name - название тега
     * @return Tag - если тег найден и null - если нет
     * */
     Tag findTagByName(String name);

    /**
     * Добавляет новый тег в таблицу
     * @param name - наименование тега
     * @param shortDescription - краткое описание тега
     * */
    void addTag(String name, String shortDescription);

    /**
     * Поиск постов по тегу
     * @param id - id тега
     * @return список постов по данному тегу
     * */
    List<Post> postFindByTagId(Long id);
}
