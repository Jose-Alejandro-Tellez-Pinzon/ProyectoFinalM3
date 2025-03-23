package com.devsenior.atellez_jbenavides.service;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.atellez_jbenavides.exeption.NotFoundExeption;
import com.devsenior.atellez_jbenavides.model.Loan;
import com.devsenior.atellez_jbenavides.model.LoanState;

public class LoanService {

    private List<Loan> loans;
    private BookService bookService;
    private UserService userService;

    public LoanService(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loans = new ArrayList<>();
    }

    public void addLoan(String id, String isbn) throws NotFoundExeption {

        var book = bookService.getBookByIsbn(isbn);
        var user = userService.getUserById(id);

        loans.add(new Loan(user, book));

    }

    public void returnBook(String id, String isbn) throws NotFoundExeption {

        for (var loan : loans) {
            if (loan.getUser().getId().equals(id)
                    && loan.getBook().getIsbn().equals(isbn)
                    && loan.getLoanDate().equals(LoanState.STARTED)) {
                loan.setState(LoanState.FINISHED);
                return;
            }

        }

        throw new NotFoundExeption("El prestamo no se encuentra en la base de datos");
    }
}
