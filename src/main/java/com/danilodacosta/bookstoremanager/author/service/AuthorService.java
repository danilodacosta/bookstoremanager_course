package com.danilodacosta.bookstoremanager.author.service;

import com.danilodacosta.bookstoremanager.author.dto.AuthorDTO;
import com.danilodacosta.bookstoremanager.author.entity.Author;
import com.danilodacosta.bookstoremanager.author.entity.repository.AuthorRepository;
import com.danilodacosta.bookstoremanager.author.exception.AuthorAlreadyExistsException;
import com.danilodacosta.bookstoremanager.author.exception.AuthorNotFoundException;
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

    public AuthorDTO create(AuthorDTO authorDTO) {
       verifyExists(authorDTO.getName());
       Author authorToCreate =  authorMapper.toModel(authorDTO);
       Author createdAuthor =  authorRepository.save(authorToCreate);
       return authorMapper.toDTO(createdAuthor);
    }

    public AuthorDTO findById(Long id) {

        Author foundAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        return authorMapper.toDTO(foundAuthor);
    }


    private void verifyExists(String authorName) {
        authorRepository.findByName(authorName)
                .ifPresent( author -> { throw new AuthorAlreadyExistsException(authorName); });
    }
}
