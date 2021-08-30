package com.danilodacosta.bookstoremanager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @GetMapping
    @ApiOperation("Return an example hello world")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success method return")
    })
    public String hello() {
        return "Hello book store manager";
    }
}
