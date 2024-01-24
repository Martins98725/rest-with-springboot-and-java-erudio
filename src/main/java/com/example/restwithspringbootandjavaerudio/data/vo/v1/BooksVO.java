package com.example.restwithspringbootandjavaerudio.data.vo.v1;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "name", "author", "publicationDate"})
public class BooksVO extends RepresentationModel<BooksVO> implements Serializable {
    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String name;
    private String author;
    private String publicationDate;

    public BooksVO(Long key, String name, String author, String publicationDate) {
        this.key = key;
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
    }


    public BooksVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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
        if (!super.equals(o)) return false;
        BooksVO booksVO = (BooksVO) o;
        return Objects.equals(key, booksVO.key) && Objects.equals(name, booksVO.name) && Objects.equals(author, booksVO.author) && Objects.equals(publicationDate, booksVO.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, name, author, publicationDate);
    }
}
