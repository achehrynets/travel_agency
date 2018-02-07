package web.action.country;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCountryPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(AddCountryPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        LOGGER.trace("Forward to " + Path.PAGE_ADD_COUNTRY);
        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_ADD_COUNTRY};
    }
}
