package web.action.tour;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.TourDAO;
import db.DAOFactory;
import db.entity.Tour;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourInfoPage implements Action {

    private static final Logger LOGGER = Logger.getLogger(TourInfoPage.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        TourDAO tourDAO = factory.tourDAO();

        Long id = Long.parseLong(req.getParameter("id"));

        LOGGER.trace("Request parameter id: " + id);

        Tour tour = tourDAO.getTourById(id);

        LOGGER.trace("Found in DB: " + tour);

        req.setAttribute("tour", tour);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);

        return new String[]{AppConst.FORWARD, Path.PAGE_INFO_ABOUT_TOUR};
    }
}
