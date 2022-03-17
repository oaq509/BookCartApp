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
public class ViewCartServlet extends HttpServlet {

    @EJB
    private CartBeansLocal CartBeans;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>List of Books</title>\n"
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
                    + "        <div class=\"column1\">");

            if (CartBeans.pringCart().length == 0) {
                out.println("<p>Your Cart is Empty \n</p>");
                out.println("<p>Please add books to your cart \n</p>");
            } else {
                out.println("            <h1>Your Cart</h1>\n"
                        + "            <table>\n"
                        + "                <tr>\n"
                        + "                    <th>ID</th>\n"
                        + "                    <th>Name</th>\n"
                        + "                    <th>Author</th>\n"
                        + "                    <th>Price</th>\n"
                        + "                    <th>Delete</th>\n"
                        + "                </tr>\n"
                        + "                <tr>");

                int i, j;
                for (i = 0; i < CartBeans.pringCart().length; i++) {
                    for (j = 0; j < 4; j++) {
                        out.print("<td>" + CartBeans.pringCart()[i][j] + "</td>\n");
                    }
                    out.print("<td>" + "    <form action=\"CartServlet\" method=\"post\">\n"
                            + "        <input type=\"hidden\" name=\"delBookID\" value=\"" + CartBeans.pringCart()[i][j - 4] + "\">\n"
                            + "        <input type=\"submit\" class=\"delete\" value=\"X\">\n"
                            + "    </form>" + "</td>");
                    out.print("</tr>\n");
                }
                out.print("</table>             <br><p style=\"display: inline-block; font-size: 20px;\"><b>Total =</b> " + CartBeans.totalPrice() + " sar " + "</p>\n"
                        + "            <form action=\"CartServlet\" method=\"post\" style=\"display: inline;\">\n"
                        + "                <input type=\"hidden\" name=\"buy\">\n"
                        + "                <a href=\"payment.html\"><input type=\"submit\" value=\"buy\" class=\"buy\"></a>\n"
                        + "            </form>"
                        + "            <br>\n");
            }
            out.println("<br><a href=\"index.html\"><button>Return to home page</button></a><br>\n"
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
                    + "            grid-template-columns: 1fr 3fr;\n"
                    + "            grid-template-areas: \"column column1\";"
                    + "            background-color: #eee;\n"
                    + "            min-height: 600px;\n"
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
                    + "            min-height: 570px;\n"
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
                    + "            margin-top: 190px;\n"
                    + "            padding: 20px;\n   "
                    + "        }\n"
                    + "\n"
                    + "        .column1 button {\n"
                    + "            font-size: 17px;\n"
                    + "            cursor: pointer;\n"
                    + "        }\n"
                    + "        .buy {\n"
                    + "            background-color: green;\n"
                    + "            margin-left: 40%;\n"
                    + "            width: 90px;\n"
                    + "            height: 40px;\n"
                    + "            padding: 7px;\n"
                    + "            font-size: 16px;\n"
                    + "            border-radius: 5px;\n"
                    + "            background-color: rgb(42, 164, 97);\n"
                    + "            border: none;\n"
                    + "            text-decoration: none;\n"
                    + "            cursor: pointer;            \n"
                    + "        }\n"
                    + "\n"
                    + "        .buy:hover {\n"
                    + "            background-color: rgb(92, 216, 137);\n"
                    + "        }    "
                    + "        .delete {\n"
                    + "            background-color: darkred;\n"
                    + "            color: white;\n"
                    + "            border: none;\n"
                    + "            text-decoration: none;\n"
                    + "            cursor: pointer;    \n"
                    + "        }"
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
