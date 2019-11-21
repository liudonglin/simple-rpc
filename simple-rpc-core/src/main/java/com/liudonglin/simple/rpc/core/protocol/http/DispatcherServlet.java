package com.liudonglin.simple.rpc.core.protocol.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private static final HttpHandler processor = new HttpHandler();

    private static DispatcherServlet INSTANCE = new DispatcherServlet();

    private DispatcherServlet(){}

    public static DispatcherServlet getInstance() {
        return INSTANCE;
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processor.handle(req,res);
    }
}
