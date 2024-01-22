package com.example.restwithspringbootandjavaerudio.repositorys;

import com.example.restwithspringbootandjavaerudio.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
