/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookcart.app;

import javax.ejb.Local;

/**
 *
 * @author Owais
 */
@Local
public interface CartBeansLocal {

    public void addBook(int bookId);
    public void deleteBook(int bookId);
    public String[][] pringCart();
    public int bookExist(int bookId);
    public int totalPrice();
    public void purchase();

}
