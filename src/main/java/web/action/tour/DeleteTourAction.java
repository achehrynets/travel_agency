package web.action.tour;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.TourDAO;
import db.DAOFactory;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteTourAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(DeleteTourAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        long id = Long.valueOf(req.getParameter("id"));
        LOGGER.trace("Request parameter id: " + id);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        TourDAO tourDAO = factory.tourDAO();
        tourDAO.deleteTourById(id);

        LOGGER.trace("Tour successfully delete");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Tour successfully delete");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_TOURS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Tour successfully delete");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_TOURS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
