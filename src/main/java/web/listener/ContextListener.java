package web.listener;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import constant.AppConst;
import constant.InfoMessages;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        initLog4J(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void initLog4J(ServletContext servletContext) {
        PropertyConfigurator.configure(
                servletContext.getRealPath(AppConst.LOG4J_PROPERTIES_PATH)
        );
        LOGGER.info(InfoMessages.INFO_LOG4J_INIT);
    }

}
