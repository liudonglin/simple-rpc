package com.liudonglin.simple.rpc.core.protocol.http;

import com.liudonglin.simple.rpc.core.common.URL;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {

    public void start(URL url) {
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(url.getPort());

        Engine engine = new StandardEngine();
        engine.setDefaultHost(url.getHost());

        Host host = new StandardHost();
        host.setName(url.getHost());

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath, "dispatcher", DispatcherServlet.getInstance());
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            tomcat.start();
            System.out.printf("tomcat is start at %s:%d \n",url.getHost(),url.getPort());
            server.await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

}
