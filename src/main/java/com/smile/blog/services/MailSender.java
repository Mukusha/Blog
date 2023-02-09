package com.smile.blog.services;

import org.springframework.stereotype.Component;

@Component
public interface MailSender {
    /**
     * Рассылка почты
     * @param emailTo - куда отправлять
     * @param subject - тема письма
     * @param message - сообщение
     * */
    void send(String emailTo, String subject, String message);
}
