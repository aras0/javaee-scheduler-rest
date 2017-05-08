package pl.javaee.example.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.javaee.example.services.ServiceCurrency;

@WebServlet(name = "testServlet", urlPatterns = {"/rest"})
public class RestCurrency {

private static final long serialVersionUID = 2638127270022516617L;
    
    @Inject
    private ServiceCurrency service;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String val = request.getParameter("val");
        String codeIn = request.getParameter("codeIn");
        String codeOut = request.getParameter("codeOut");
        
        PrintWriter out = response.getWriter();
        out.println(service.changeCurrency(val, codeIn, codeOut));
        out.close();
    }

}
