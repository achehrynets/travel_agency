package web.action.resort;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.ResortDAO;
import db.DAOFactory;
import db.entity.Resort;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditResortAction implements Action{

    private static final Logger LOGGER = Logger.getLogger(EditResortAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        Resort resort = new Resort();
        resort.setId(id);
        resort.setName(name);
        resort.setDescription(description);

        LOGGER.trace("Request params: " + resort);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        ResortDAO resortDAO = factory.resortDAO();
        resortDAO.updateResort(resort);

        LOGGER.trace("Resort with id " + resort.getId() + " was update");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Resort was update");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_RESORTS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE + " --- " + "Resort was updated");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_RESORTS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
