package com.example.restwithspringbootandjavaerudio;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.BooksVO;
import com.example.restwithspringbootandjavaerudio.model.Books;

import java.util.ArrayList;
import java.util.List;

public class MockBook {
    public Books mockEntity(){
        return mockEntity(0);
    }

    public BooksVO mockVO(){
        return mockVO(0);
    }

    public List<Books> mockEntityList(){
        List<Books> books = new ArrayList<>();
        for (int i = 0; i < 14; i++){
            books.add(mockEntity(i));
        }
        return books;
    }




    public Books mockEntity(Integer number) {
        Books book = new Books();

        book.setName("Name of the book test: " + number);
        book.setAuthor("Name of author test: " + number);
        book.setId(number.longValue());
        book.setPublicationDate("Publication date test: " + number);
        return book;
    }
    public BooksVO mockVO(Integer number){
        BooksVO books = new BooksVO();
        books.setName("Name of the book test: " + number);
        books.setAuthor("Name of author test: " + number);
        books.setKey(number.longValue());
        books.setPublicationDate("Publication date test: " + number);
        return books;
    }
}
