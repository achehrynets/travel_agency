package web.listener;


import constant.AppConst;
import constant.InfoMessages;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.debug("Context  init");
        ServletContext context = servletContextEvent.getServletContext();
        initLog4J(context);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.debug("Context destroy");
    }

    private void initLog4J(ServletContext servletContext) {
        PropertyConfigurator.configure(
                servletContext.getRealPath(AppConst.LOG4J_PROPERTIES_PATH)
        );
        LOGGER.info(InfoMessages.INFO_LOG4J_INIT);
    }

}
