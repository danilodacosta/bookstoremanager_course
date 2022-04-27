package com.danilodacosta.bookstoremanager.books.repository;

import com.danilodacosta.bookstoremanager.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
