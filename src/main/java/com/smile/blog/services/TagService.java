package com.smile.blog.services;

import com.smile.blog.models.Tag;

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
}
