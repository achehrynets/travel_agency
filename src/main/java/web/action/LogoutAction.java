package web.action;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.entity.User;
import exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(LogoutAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        LOGGER.trace("User logout. user login ---> "  + user.getLogin());
        if (session != null) {
            session.invalidate();
        }
        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.REDIRECT, Path.PAGE_MAIN};
    }
}
