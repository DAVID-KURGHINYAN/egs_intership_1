package com.airport2.servlet;

import com.airport2.dao.AddressDaoImpl;
import com.airport2.entity.Address;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/address")
public class DemoServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("airport2");

        EntityManager entitymanager = emFactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Address address  = entitymanager.find(Address.class, Long.valueOf(id));
        entitymanager.getTransaction().commit();

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(address.getStreet());

    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        StringBuffer jb = new StringBuffer();
//        String line;
//
//        BufferedReader reader = request.getReader();
//        while ((line = reader.readLine()) != null) {
//            jb.append(line);
//        }
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        Address address = gson.fromJson(jb.toString(), Address.class);
//
//
//        AddressDaoImpl impl = new AddressDaoImpl();
//        impl.getById(address.getId());
//        //call DAO method
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String emailId = req.getParameter("emailId");
        String password = req.getParameter("password");

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<html>");
        printWriter.print("<body>");
        printWriter.print("<h1>Student Resistration Form Data</h1>");
        printWriter.print("<p> firstName :: " + firstName + "</p>");
        printWriter.print("<p> lastName :: " + firstName + "</p>");
        printWriter.print("<p> firstName :: " + firstName + "</p>");
        printWriter.print("<p> firstName :: " + firstName + "</p>");
        printWriter.print("</body>");
        printWriter.print("</html>");
        printWriter.close();

        System.out.println("firstName :: " + firstName);
        System.out.println("lastName :: " + lastName);
        System.out.println("emailId :: " + emailId);
        System.out.println("password :: " + password);
    }
}


