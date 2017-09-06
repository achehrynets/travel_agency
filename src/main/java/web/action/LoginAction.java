package web.action;

import constant.AppConst;
import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import db.DAOFactory;
import db.DBName;
import db.entity.Role;
import db.entity.User;
import db.postgresql.DAO.UserDAO;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import util.EncryptUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction implements Action{

    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);

    @Override
    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String forward = Path.PAGE_LOGIN_PAGE;

        LOGGER.trace("User login:  " + login);

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new ApplicationException(ErrorMessages.ERROR_EMPTY_LOGIN_OR_PASSWORD, forward);
        }

        DAOFactory factory = DAOFactory.getDaoFactory(DBName.POSTGRESQL);
        UserDAO userDAO = factory.userDAO();

        User user = userDAO.getUserByLogin(login);
        LOGGER.trace("Found in DB user:  " + user);
        if (user == null) {
            throw new ApplicationException(ErrorMessages.ERROR_INCORRECT_LOGIN, forward);
        }

        password = EncryptUtil.encryptPassword(password);

        if (!password.equals(user.getPassword())) {
            throw new ApplicationException(ErrorMessages.ERROR_INCORRECT_PASSWORD, forward);
        }

        Role role = Role.getRole(user);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        LOGGER.trace("Set the session attribute: user --- " + user);

        session.setAttribute("role", role);
        LOGGER.trace("Set the session attribute: role --- " + role);
        LOGGER.trace("User " + user + " logged as " + role.getName().toLowerCase());

        switch (role) {
            case ADMIN: {
                forward = Path.REGISTRATION_PAGE;
                break;
            }
            case MANAGER: {
                forward = null;
                break;
            }
            case CLIENT: {
                forward = null;
                break;
            }
        }
        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.REDIRECT, forward};
    }
}
