package com.danilodacosta.bookstoremanager.author.service;

import com.danilodacosta.bookstoremanager.author.entity.repository.AuthorRepository;
import com.danilodacosta.bookstoremanager.author.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final static AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


}
