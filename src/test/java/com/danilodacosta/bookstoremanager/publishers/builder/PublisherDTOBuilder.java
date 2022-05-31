package com.danilodacosta.bookstoremanager.publishers.builder;

import com.danilodacosta.bookstoremanager.publishers.dto.PublisherDTO;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class PublisherDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name = "Danilo Editora";

    @Builder.Default
    private final String code = "DALU1234";

    @Builder.Default
    private final LocalDate foundationDate = LocalDate.of(2022, 05, 31);

    public PublisherDTO buildPublisherDTO() {
        return new PublisherDTO(id, name, code , foundationDate);
    }

}
