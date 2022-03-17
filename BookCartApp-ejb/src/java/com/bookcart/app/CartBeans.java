/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookcart.app;

import java.util.ArrayList;
import javax.ejb.Stateful;
import static com.bookcart.app.BookBeans.Books;

/**
 *
 * @author Owais
 */
@Stateful
public class CartBeans implements CartBeansLocal {

    // Add business logic below.
    static ArrayList<LibBook> cart = new ArrayList<LibBook>();

    @Override
    public void addBook(int bookId) {
        for (int i = 0; i < Books.size(); i++) {
            if (Books.get(i).getBookId() == bookId && bookExist(bookId) == -1) {
                cart.add(new LibBook(Books.get(i).getBookId(), Books.get(i).getBookTitle(), Books.get(i).getBookAuthor(), Books.get(i).getBookPrice()));
            }
        }
    }

    @Override
    public void deleteBook(int bookId) {
        if (cart.size() > 0) {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getBookId() == bookId) {
                    cart.remove(i);
                    return;
                }
            }
        }
    }

    @Override
    public int bookExist(int bookId) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getBookId() == bookId) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public int totalPrice() {
        int total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total += cart.get(i).bookPrice;
        }
        return total;
    }

    @Override
    public String[][] pringCart() {
        String[][] booksToPrint = new String[cart.size()][4];
        for (int i = 0; i < cart.size(); i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0: {
                        int temp1 = cart.get(i).getBookId();
                        String temp2 = temp1 + "";
                        booksToPrint[i][j] = temp2;
                        break;
                    }
                    case 1:
                        booksToPrint[i][j] = cart.get(i).getBookTitle();
                        break;
                    case 2:
                        booksToPrint[i][j] = cart.get(i).getBookAuthor();
                        break;
                    case 3: {
                        Double temp1 = cart.get(i).getBookPrice();
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

    @Override
    public void purchase() {
        cart.clear();
    }

}
