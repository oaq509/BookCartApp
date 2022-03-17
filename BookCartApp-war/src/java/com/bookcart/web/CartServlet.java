/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookcart.web;

import com.bookcart.app.CartBeansLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Owais
 */
public class CartServlet extends HttpServlet {

    @EJB
    private CartBeansLocal CartBeans;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            // Strin message - will print the feedback when (Adding a book, Deleting a Book, or if Library is empty)
            String message = "";
            if (request.getParameterMap().containsKey("delBookID")) {
                int BID = Integer.parseInt((request.getParameter("delBookID")));
                CartBeans.deleteBook(BID);
                message = "<p style=\"font-weight: bold; font-size: 20px;\">" + "Book removed from your cart" + "</p> \n";
            }
            if (request.getParameterMap().containsKey("bookID")) {
                int BID = Integer.parseInt((request.getParameter("bookID")));
                switch (CartBeans.bookExist(BID)) {
                    case 1:
                        message = "<p style=\"font-weight: bold; font-size: 20px;\">" + "This book is already on your cart" + "</p> \n";
                        break;
                    case -1:
                        CartBeans.addBook(BID);
                        message = "<p style=\"font-weight: bold; font-size: 20px; color: green;\">" + "Book added successfully to your cart" + "</p> \n";
                        break;
                    default:
                        message = "<p style=\"font-weight: bold; font-size: 20px;\">" + "Your Cart is empty" + "</p> \n" + "<p>Go to list of books to view" + "</p> \n";
                        break;
                }
            }
            if (request.getParameterMap().containsKey("buy")) {
                message = "<p style=\"font-weight: bold; font-size: 20px; color: green;\">" + "Purchase completed,<br>Thank you for your purchase." + "</p> \n";
                CartBeans.purchase();
            }
            out.println("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Book Library</title>\n"
                    + "     <link rel=\"icon\" type=\"image/x-icon\" href=\"https://cdn-icons-png.flaticon.com/512/225/225932.png\">"
                    + "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
                    + "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
                    + "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@200&display=swap\" rel=\"stylesheet\">\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <div class=\"container\">\n"
                    + "        <div class=\"column\">\n"
                    + "            <div class=\"row\">\n"
                    + "                <img src=\"https://cdn-icons-png.flaticon.com/512/225/225932.png\" alt=\"\" width=\"150\">\n"
                    + "                <h1>Book Library</h1>\n"
                    + "                <p>Welcome To Book Library System</p>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "        <div class=\"column1\">\n"
                    + message
                    + "            <br>\n"
                    + "            <a href=\"index.html\"><button>Return to home page</button></a>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "\n"
                    + "\n"
                    + "    <style>\n"
                    + "        body {\n"
                    + "            background-color: rgb(220, 220, 220);\n"
                    + "        }\n"
                    + "\n"
                    + "        .container {\n"
                    + "            display: grid;\n"
                    + "            grid-template-columns: auto auto auto;\n"
                    + "            background-color: #eee;\n"
                    + "            height: 600px;\n"
                    + "            width: 90%;\n"
                    + "            max-width: 1100px;\n"
                    + "            margin: 100px auto auto auto;\n"
                    + "            font-family: 'Roboto Mono', monospace;\n"
                    + "            border-radius: 15px;\n"
                    + "            border: 2px solid rgb(88, 88, 88);\n"
                    + "        }\n"
                    + "\n"
                    + "        .column {\n"
                    + "            text-align: center;\n"
                    + "            padding: 15px;\n"
                    + "            background-color: rgb(211, 211, 211);\n"
                    + "            ;\n"
                    + "            height: 570px;\n"
                    + "            width: 300px;\n"
                    + "            border-radius: 15px 0px 0px 15px;\n"
                    + "            border-right: 2px solid rgb(88, 88, 88);\n"
                    + "        }\n"
                    + "\n"
                    + "        .column img {\n"
                    + "            display: block;\n"
                    + "            margin: auto;\n"
                    + "        }\n"
                    + "\n"
                    + "        .column1 h1 {\n"
                    + "            text-align: left;\n"
                    + "        }\n"
                    + "\n"
                    + "        .row {\n"
                    + "            margin-top: 100px;\n"
                    + "        }\n"
                    + "\n"
                    + "        table {\n"
                    + "            width: 100%;\n"
                    + "        }\n"
                    + "\n"
                    + "        table th,\n"
                    + "        td {\n"
                    + "            border: 1px solid;\n"
                    + "            text-align: center;\n"
                    + "        }\n"
                    + "\n"
                    + "        .column1 {\n"
                    + "            margin-top: 200px;\n"
                    + "        }\n"
                    + "\n"
                    + "        .column1 button {\n"
                    + "            text-align: left;\n"
                    + "            font-size: 17px;\n"
                    + "            cursor: pointer;\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "</body>\n"
                    + "\n"
                    + "\n"
                    + "</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
