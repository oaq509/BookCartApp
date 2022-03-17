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
public interface BookBeansLocal {
     public void addBook(int bookId, String bookTitle, String bookAuthor, double bookPrice);
     public void deleteBook(int bookId);
     public int bookExist(int bookId);
     public String[][] pringBooks();
}
