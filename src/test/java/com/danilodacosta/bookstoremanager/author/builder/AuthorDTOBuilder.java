package com.danilodacosta.bookstoremanager.author.builder;

import com.danilodacosta.bookstoremanager.author.dto.AuthorDTO;
import lombok.Builder;

@Builder
public class AuthorDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name= "Danilo Pereira";

    @Builder.Default
    private final Integer age = 31;

    public AuthorDTO authorDTOBuilder() {
        return new AuthorDTO(id, name, age);
    }
}
