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

public class SetHotTourAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(SetHotTourAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        long tourId = Long.parseLong(req.getParameter("tour_id"));

        LOGGER.trace("Request parameter tour id: " + tourId);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        TourDAO tourDAO = factory.tourDAO();
        tourDAO.setHotTourById(tourId);

        LOGGER.trace("Tour successfully update");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Tour successfully update");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_TOURS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Tour successfully update");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_TOURS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
