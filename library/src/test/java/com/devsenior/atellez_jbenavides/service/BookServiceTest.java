package com.devsenior.atellez_jbenavides.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.atellez_jbenavides.exception.NotFoundException;

public class BookServiceTest {

    private BookService service;

    @BeforeEach
    void setup() {
        service = new BookService();
    }

    @Test
    void testAddBook() throws NotFoundException {
        // GIVEN - Preparar los datos de la prueba
        var isbn = "123456789";
        var title = "Aprendiendo Java";
        var author = "Cesar Diaz";

        // WHEN - Ejecutar el metodo a probar
        service.addBook(isbn, title, author);

        // THEN - Verificaciones que el metodo se ejecutó bien
        var book = service.getBookByIsbn(isbn);
        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
    }

   @Test
    void testDeleteExistingBook() throws NotFoundException {
        // GIVEN
        var isbn = "123456789";
        var title = "Aprendiendo Java";
        var author = "Cesar Diaz";
        service.addBook(isbn, title, author);

        // WHEN
        service.deleteBook(isbn);

        // THEN
        try {
            service.getBookByIsbn(isbn);
            fail();
        } catch (NotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    void testDeleteNonExistingBook() {
        // GIVEN
        var isbn = "123456789";

        // WHEN - THEN
        assertThrows(NotFoundException.class,
                () -> {
                    service.deleteBook(isbn);
                });
    }

    @Test
    void testDeleteWithExistingBooksButNotGivenIsbn() {
        // GIVEN
        service.addBook("9876543210", "En busca de la felicidad", "Cesar Diaz");
        var isbn = "1234567890";

        // WHEN - THEN
        assertThrows(NotFoundException.class,
                () -> service.deleteBook(isbn));
    }

    @Test
    void testGetAllBooks() {
        // GIVEN

        // WHEN
        var books = service.getAllBooks();

        // THEN
        assertNotNull(books);
        assertEquals(0, books.size());
    }

    @Test
    void testGetBookByIsbnWithWrongIsbn() {
        // GIVEN
        service.addBook("1234567890", "Buscando la felicidad", "Cesar Diaz");

        var isbn = "0987654321";

        // WHEN -THEN
        assertThrows(NotFoundException.class,
                () -> service.getBookByIsbn(isbn));
    }
}
