package web.action;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.UserDAO;
import db.DAOFactory;
import db.entity.User;
import exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ViewUsersPageAction implements Action{

    private static final Logger LOGGER = Logger.getLogger(ViewUsersPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        UserDAO userDAO = factory.userDAO();

        List<User> users = userDAO.getAllUsers();
        Collections.sort(users, new User());

        LOGGER.trace("Found in DB users: " + users);

        req.setAttribute("users", users);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.FORWARD, Path.PAGE_USERS};
    }
}
