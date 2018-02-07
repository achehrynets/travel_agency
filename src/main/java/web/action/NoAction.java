package web.action;

import constant.AppConst;
import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(NoAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        String errorMessage = "No such action";
        req.setAttribute(ErrorMessages.MESSAGE, errorMessage);
        LOGGER.trace("Set the request attribute: errorMessage ---> " + errorMessage);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_ERROR};
    }
}
