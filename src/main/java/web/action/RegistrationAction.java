package web.action;

import constant.AppConst;
import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import db.DAO.UserDAO;
import db.DAOFactory;
import db.entity.User;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import util.EncryptUtil;
import util.validation.RegistrationValidation;
import util.validation.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationAction implements Action{

    private static final Logger LOGGER = Logger.getLogger(RegistrationAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        User user = extractUserFromRequest(req);
        String forward = Path.PAGE_REGISTRATION;
        if (user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty() ||
                user.getSurname().isEmpty() || user.getMiddleName().isEmpty() || user.getName().isEmpty() ||
                user.getPassportId().isEmpty() || user.getInternationalPassportId().isEmpty()) {
            throw new ApplicationException(ErrorMessages.ERROR_EMPTY_INPUT, forward);
        }

        Validation validation = new RegistrationValidation();
        validation.validateFieldsFromRequest(req);
        if (validation.isValid()) {
            String encryptPassword = EncryptUtil.encryptPassword(user.getPassword());
            user.setPassword(encryptPassword);

            LOGGER.trace("Request parameters user: " + user);
            user.setBlocked(false);
            user.setRoleId(1);

            DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
            UserDAO userDAO = factory.userDAO();

            userDAO.addUser(user);

            LOGGER.trace("User account successfully created: " + user);

            HttpSession session = req.getSession();
            session.setAttribute(InfoMessages.INFO_MESSAGE, "Your account successfully created!");
            session.setAttribute(AppConst.FORWARD, "/");

            LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE + " ---> "
                    + "Your account successfully created!");
            LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " ---> " + "/");
        } else {
            req.setAttribute(ErrorMessages.MESSAGES, validation.getErrorMessages());
            req.getRequestDispatcher(Path.PAGE_REGISTRATION).forward(req, resp);
        }

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.REDIRECT, AppConst.REDIRECT};
    }


    private User extractUserFromRequest(HttpServletRequest req) {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setSurname(req.getParameter("surname"));
        user.setMiddleName(req.getParameter("middle_name"));
        user.setName(req.getParameter("name"));
        user.setPassportId(req.getParameter("passport_id"));
        user.setInternationalPassportId(req.getParameter("international_passport_id"));

        return user;
    }
}
