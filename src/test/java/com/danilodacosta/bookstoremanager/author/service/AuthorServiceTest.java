package com.danilodacosta.bookstoremanager.author.service;

import com.danilodacosta.bookstoremanager.author.builder.AuthorDTOBuilder;
import com.danilodacosta.bookstoremanager.author.dto.AuthorDTO;
import com.danilodacosta.bookstoremanager.author.entity.repository.AuthorRepository;
import com.danilodacosta.bookstoremanager.author.mapper.AuthorMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private AuthorDTOBuilder authorDTOBuilder;

    @BeforeEach
    void setUp(){
        authorDTOBuilder = AuthorDTOBuilder.builder().build();
        AuthorDTO authorDTO = authorDTOBuilder.authorDTOBuilder();
    }

}
