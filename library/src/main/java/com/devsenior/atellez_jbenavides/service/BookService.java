package com.devsenior.atellez_jbenavides.service;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.atellez_jbenavides.exeption.NotFoundExeption;
import com.devsenior.atellez_jbenavides.model.Book;

public class BookService {

    private List<Book> books;

    public BookService(){

        books = new ArrayList<>(); 
    }

    public void addBook(String isbn, String title, String author) {
        books.add(new Book(isbn, title, author));
    }

    public List<Book> getAllBooks() {

        return books;
    }

    public Book getBookByIsbn(String isbn) throws NotFoundExeption {

        for (var book : books) {

            if (book.getIsbn().equals(isbn)) {
                return book;
            }

        }
        throw new NotFoundExeption("El libro con ISBN " + isbn + " no se encuentra en la base de datos");
    }

    public void deleteBook(String isbn) throws NotFoundExeption {

        for (var book : books) {

            if (book.getIsbn().equals(isbn)) {
                books.remove(book);

                return;
            }
        }
        throw new NotFoundExeption("El libro con ISBN " + isbn + " no se encuentra en la base de datos");
    }

}
