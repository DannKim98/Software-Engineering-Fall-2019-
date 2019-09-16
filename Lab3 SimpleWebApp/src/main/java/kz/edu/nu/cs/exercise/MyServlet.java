package kz.edu.nu.cs.exercise;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

@WebServlet(urlPatterns = { "/myservlet" })
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ArrayList<String> logs = new ArrayList<String>();
    
    public MyServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.getWriter().append("Served at: ").append(request.getContextPath());
    	HttpSession session = request.getSession();
        Date date = new Date(session.getLastAccessedTime());
        
        logs.add(date.toString());
        logs.add(request.getRemoteHost());
        logs.add(request.getContextPath() + request.getServletPath());
        System.out.println(logs);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
