package com.danilodacosta.bookstoremanager.publishers.service;

import com.danilodacosta.bookstoremanager.publishers.builder.PublisherDTOBuilder;
import com.danilodacosta.bookstoremanager.publishers.mapper.PublisherMapper;
import com.danilodacosta.bookstoremanager.publishers.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {

    private final PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    private PublisherDTOBuilder publisherDTOBuilder;

    @BeforeEach
    void setUp(){
        publisherDTOBuilder = PublisherDTOBuilder.builder().build();
    }

}
