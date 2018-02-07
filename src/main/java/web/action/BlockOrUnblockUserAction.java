package web.action;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.UserDAO;
import db.DAOFactory;
import exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BlockOrUnblockUserAction implements Action{

    private static final Logger LOGGER = Logger.getLogger(BlockOrUnblockUserAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        UserDAO userDAO = factory.userDAO();

        int id = Integer.valueOf(req.getParameter("id"));
        boolean isBlock = Boolean.valueOf(req.getParameter("isBlock"));

        LOGGER.trace("Request parameters: id = " + id + " isBlock = " + isBlock);

        userDAO.blockOrUnblockUserByIdAndParam(id, isBlock);
        String message;

        if (isBlock) {
            LOGGER.trace("User with id: " + id + " blocked successfully");
            message = "User was blocked successfully";
        } else {
            LOGGER.trace("User with id: " + id + " unblocked successfully");
            message = "User was unblocked successfully";
        }

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, message);
        session.setAttribute(AppConst.FORWARD, Path.ACTION_USERS_PAGE);

        LOGGER.trace("Set the session attribute " + InfoMessages.INFO_MESSAGE + ": " + message);
        LOGGER.trace("Set the session attribute " + AppConst.FORWARD + ": " + Path.ACTION_USERS_PAGE);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
