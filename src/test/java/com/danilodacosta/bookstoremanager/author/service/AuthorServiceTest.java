package com.danilodacosta.bookstoremanager.author.service;

import com.danilodacosta.bookstoremanager.author.builder.AuthorDTOBuilder;
import com.danilodacosta.bookstoremanager.author.dto.AuthorDTO;
import com.danilodacosta.bookstoremanager.author.entity.Author;
import com.danilodacosta.bookstoremanager.author.entity.repository.AuthorRepository;
import com.danilodacosta.bookstoremanager.author.exception.AuthorAlreadyExistsException;
import com.danilodacosta.bookstoremanager.author.exception.AuthorNotFoundException;
import com.danilodacosta.bookstoremanager.author.mapper.AuthorMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private AuthorDTOBuilder authorDTOBuilder;

    @BeforeEach
    void setUp() {
        authorDTOBuilder = AuthorDTOBuilder.builder().build();
    }

    @Test
    void whenNewAuthorIsInformedThenItShouldBeCreated() {
        //given
        AuthorDTO expectedAuthorToCreatedDTO = authorDTOBuilder.authorDTOBuilder();
        Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreatedDTO);

        //when
        when(authorRepository.save(expectedCreatedAuthor)).thenReturn(expectedCreatedAuthor);
        when(authorRepository.findByName(expectedAuthorToCreatedDTO.getName())).thenReturn(Optional.empty());
        AuthorDTO createdAuthorDTO = authorService.create(expectedAuthorToCreatedDTO);

        //then
        assertThat(createdAuthorDTO, is(equalTo(expectedAuthorToCreatedDTO)));
    }

    @Test
    void whenExistingAuthorIsInformedThenAnExceptionShouldBeThrown() {
        AuthorDTO expectedAuthorToCreatedDTO = authorDTOBuilder.authorDTOBuilder();
        Author expectedCreatedAuthor = authorMapper.toModel(expectedAuthorToCreatedDTO);

        when(authorRepository.findByName(expectedAuthorToCreatedDTO.getName()))
                .thenReturn(Optional.of(expectedCreatedAuthor));

        assertThrows(AuthorAlreadyExistsException.class, () -> authorService.create(expectedAuthorToCreatedDTO));
    }

    @Test
    void whenValidIdIsGivenThenAnAuthorShouldBeReturned() {
        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.authorDTOBuilder();
        Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

        when(authorRepository.findById(expectedFoundAuthorDTO.getId())).thenReturn(Optional.of(expectedFoundAuthor));

        AuthorDTO foundAuthorDTO = authorService.findById(expectedFoundAuthorDTO.getId());

        assertThat(foundAuthorDTO, is(equalTo(expectedFoundAuthorDTO)));


    }

    @Test
    void whenInvalidIdIsGivenThenAnExceptionShouldBeThrown() {
        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.authorDTOBuilder();

        when(authorRepository.findById(expectedFoundAuthorDTO.getId())).thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> authorService.findById(expectedFoundAuthorDTO.getId()));

    }

    @Test
    void whenListAuthorsIsCalledThenItShouldBeReturned() {
        AuthorDTO expectedFoundAuthorDTO = authorDTOBuilder.authorDTOBuilder();
        Author expectedFoundAuthor = authorMapper.toModel(expectedFoundAuthorDTO);

        when(authorRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundAuthor));

        List<AuthorDTO> foundAuthorsDTO = authorService.findAll();

        assertThat(foundAuthorsDTO.size(), is(1));
        assertThat(foundAuthorsDTO.get(0), is(equalTo(expectedFoundAuthorDTO)));
    }

    @Test
    void whenListAuthorsIsCalledThenEmptyListShouldBeReturned() {
        when(authorRepository.findAll()).thenReturn(Collections.emptyList());

        List<AuthorDTO> foundAuthorsDTO = authorService.findAll();

        assertThat(foundAuthorsDTO.size(), is(0));
    }

    @Test
    void whenValidAuthorIdIsGivenThenItShouldBeDeleted(){
        AuthorDTO expectedDeletedAuthorDTO = authorDTOBuilder.authorDTOBuilder();
        Author expectedFDeletedAuthor = authorMapper.toModel(expectedDeletedAuthorDTO);

        Long expectedDeletedAuthorId = expectedDeletedAuthorDTO.getId();
        doNothing().when(authorRepository).deleteById(expectedDeletedAuthorId);
        when(authorRepository.findById(expectedDeletedAuthorId)).thenReturn(Optional.of(expectedFDeletedAuthor));

        authorService.delete(expectedDeletedAuthorId);

        verify(authorRepository, times(1)).deleteById(expectedDeletedAuthorId);
        verify(authorRepository, times(1)).findById(expectedDeletedAuthorId);
    }

    @Test
    void whenInvalidAuthorIdIsGivenThenItAnExceptionShouldBeDeletedThrown() {
        var expectedInvalidAuthorId = 2L;

        when(authorRepository.findById(expectedInvalidAuthorId)).thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> authorService.delete(expectedInvalidAuthorId));

    }
}