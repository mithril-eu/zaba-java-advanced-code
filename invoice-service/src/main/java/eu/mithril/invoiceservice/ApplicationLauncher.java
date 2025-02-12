package eu.mithril.invoiceservice;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import eu.mithril.invoiceservice.context.InvoiceApplicationConfiguration;

public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context tomcatCtx = tomcat.addContext("", null);

        var webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(InvoiceApplicationConfiguration.class);
        webCtx.setServletContext(tomcatCtx.getServletContext());
        webCtx.refresh();
        webCtx.registerShutdownHook();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);

        Wrapper servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }

}
