package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
//    private final Storage storage = Config.getInstance().getStorage();
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(("Hello"));
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        Writer writer = response.getWriter();
//        writer.write("<!DOCTYPE html>\n" +
//                     "<html lang=\"en\">\n" +
//                     "<head>\n" +
//                     "    <meta charset=\"UTF-8\">\n" +
//                     "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
//                     "    <style>\n" +
//                     "        table, th, td {\n" +
//                     "            border: 1px solid black;\n" +
//                     "            border-collapse: collapse;\n" +
//                     "        }\n" +
//                     "        th, td {\n" +
//                     "            background-color: #96D4D4;\n" +
//                     "        }\n" +
//                     "    </style>\n" +
//                     "</head>\n" +
//                     "<body>\n" +
//                     "<h2 style=\"color: darkorange\" >Resume list</h2>\n" +
//                     "<table>\n" +
//                     "    <tr>\n" +
//                     "        <th>Name</th>\n" +
//                     "        <th>Email</th>\n" +
//                     "    </tr>");
//        for (Resume resume : storage.getAllSorted()) {
//            writer.write("<tr>" +
//                    "     <td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>\n" +
//                    "     <td>" + resume.getContacts(ContactType.EMAIL) + "</td>\n" +
//                    "</tr>\n");
//        }
//        writer.write("</table>\n" +
//                     "</section>\n" +
//                     "</body>\n" +
//                     "</html>\n");
    }
}