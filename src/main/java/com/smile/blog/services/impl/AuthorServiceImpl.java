package com.smile.blog.services.impl;

import com.smile.blog.models.Author;
import com.smile.blog.repositories.AuthorRepository;
import com.smile.blog.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Value("${upload.path}")
    private String uploadPath;

    private static AuthorRepository authorRepository;


    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author profileAdd(String nickname){
        //позже добавить сохранение даты
        Author author = new Author( nickname);
        authorRepository.save(author);
        return author;
    }
    @Override
    public Author findAuthorById(Long id){
        if(!authorRepository.existsById(id)){
            return null;
        }
        Optional<Author> authors = authorRepository.findById(id);
        return authors.get();
    }


    @Override
    public Long findIdByNickname(String nickname){
        Long id=0l;

        if(!authorRepository.existsById(id)){
            return null;
        }
        Optional<Author> authors = authorRepository.findById(id);
        Author author = authors.get();
        return id;
    }

    @Override
    public void AuthorSaveUpdate(Long id, String nickname, String shortInformation, String dateOfBirth, MultipartFile file) throws IOException, ParseException {

        Author author=authorRepository.findById(id).orElseThrow();
        author.setNickname(nickname);
        author.setShortInformation(shortInformation);

        //обработка картинки
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            //надо бы сделать, что бы во время редактирования менялась картинка
            // сделать кнопку отменить

            // когда сохранена новая фотка - удалить старую из памяти
                File oldImage = new File(uploadPath+ "/" + author.getFilenameImage());
                oldImage.delete();

            //если есть картинка, то обновить ее
            author.setFilenameImage(resultFilename);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(dateOfBirth);
        Timestamp t = new Timestamp( parsedDate.getTime());
        author.setDateOfBirth(t);
        authorRepository.save(author);
    }

}
