/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookcart.app;

import static com.bookcart.app.BookBeans.Books;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Owais
 */
@Stateless
public class BookBeans implements BookBeansLocal {

    // Add business logic below.
    static ArrayList<LibBook> Books = new ArrayList<LibBook>();

    @Override
    public void addBook(int bookId, String bookTitle, String bookAuthor, double bookPrice) {
        if (bookTitle.isEmpty() || bookAuthor.isEmpty()) {
            return;
        }

        for (int i = 0; i < Books.size(); i++) {
            if (Books.get(i).getBookTitle().contains(bookTitle)
                    || Books.get(i).getBookId() == bookId) {
                return;
            }
        }
        Books.add(new LibBook(bookId, bookTitle, bookAuthor, bookPrice));
    }

    @Override
    public void deleteBook(int bookId) {
        if (Books.size() > 0) {
            for (int i = 0; i < Books.size(); i++) {
                if (Books.get(i).getBookId() == bookId) {
                    Books.remove(i);
                    return;
                }
            }
        }
    }

    @Override
    public int bookExist(int bookId) {
        for (int i = 0; i < Books.size(); i++) {
            if (Books.get(i).getBookId() == bookId) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public String[][] pringBooks() {
        String[][] booksToPrint = new String[Books.size()][4];
        for (int i = 0; i < Books.size(); i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0: {
                        int temp1 = Books.get(i).getBookId();
                        String temp2 = temp1 + "";
                        booksToPrint[i][j] = temp2;
                        break;
                    }
                    case 1:
                        booksToPrint[i][j] = Books.get(i).getBookTitle();
                        break;
                    case 2:
                        booksToPrint[i][j] = Books.get(i).getBookAuthor();
                        break;
                    case 3: {
                        Double temp1 = Books.get(i).getBookPrice();
                        String temp2 = temp1 + "";
                        booksToPrint[i][j] = temp2;
                        break;
                    }
                    default:
                        break;
                }
            }
        }
        return booksToPrint;
    }
}
