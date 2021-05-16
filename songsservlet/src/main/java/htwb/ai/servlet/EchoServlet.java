package htwb.ai.servlet;

import javax.servlet.http.HttpServlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.resource.jdbc.spi.PhysicalConnectionHandlingMode;
import org.hibernate.resource.jdbc.spi.PhysicalConnectionHandlingMode.*;
import org.apache.commons.io.IOUtils;

//@WebServlet("/echo")
public class EchoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String uriToDB = null;

    @Override
    public void init(ServletConfig servletConfig)
            throws ServletException {
        // Beispiel: Laden eines Initparameters
        // aus der web.xml
        this.uriToDB = servletConfig
                .getInitParameter("uriToDB");
        //PhysicalConnectionHandlingMode.DELAYED_ACQUISITION_AND_RELEASE_AFTER_STATEMENT
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        // alle Parameternamen (keys)
        Enumeration<String> paramNames = request
                .getParameterNames();

        String responseStr = "";
        String param = "";
        while (paramNames.hasMoreElements()) {
            param = paramNames.nextElement();
            responseStr = responseStr + param + "="
                    + request.getParameter(param) + "\n";
        }

        System.out.println ("ECHO-SERVLET: " + responseStr );
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            responseStr += "\n Your request will be sent to "
                    + uriToDB;
            out.println(responseStr);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        ServletInputStream inputStream = request
                .getInputStream();
        byte[] inBytes = IOUtils.toByteArray(inputStream);
        try (PrintWriter out = response.getWriter()) {
            out.println(new String(inBytes));
        }
    }

    protected String getUriToDB () {
        return this.uriToDB;
    }
}
