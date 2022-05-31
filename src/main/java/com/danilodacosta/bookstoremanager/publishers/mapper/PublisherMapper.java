package com.danilodacosta.bookstoremanager.publishers.mapper;

import com.danilodacosta.bookstoremanager.publishers.dto.PublisherDTO;
import com.danilodacosta.bookstoremanager.publishers.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    Publisher toModel(PublisherDTO publisherDTO);

    PublisherDTO toDTO(Publisher publisher);
}
