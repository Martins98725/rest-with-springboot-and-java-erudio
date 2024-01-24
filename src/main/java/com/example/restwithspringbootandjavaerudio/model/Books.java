package com.example.restwithspringbootandjavaerudio.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Books implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String author;
    @Column(length = 8, nullable = false)
    private String publicationDate;

    public Books() {
    }

    public Books(Long id, String name, String author, String publicationDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(id, books.id) && Objects.equals(name, books.name) && Objects.equals(author, books.author) && Objects.equals(publicationDate, books.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, publicationDate);
    }
}
