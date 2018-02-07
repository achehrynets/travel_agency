package web.action.tour;

import constant.AppConst;
import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import db.DAO.TourDAO;
import db.DAOFactory;
import db.entity.Tour;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import org.apache.taglibs.standard.extra.spath.ParseException;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchTourAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(SearchTourAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        Integer tourTypeId = null;
        Float price = null;
        Integer peopleAmount = null;
        Integer stars = null;
        try {
            tourTypeId = Integer.parseInt(req.getParameter("tour_type"));
            price = Float.parseFloat(req.getParameter("price"));
            peopleAmount = Integer.parseInt(req.getParameter("people_amount"));
            stars = Integer.parseInt(req.getParameter("hotel_stars"));
        } catch (NumberFormatException ex) {
            req.setAttribute(ErrorMessages.MESSAGE, "Please write correct values");
            req.getRequestDispatcher("/").forward(req, resp);
        }
        LOGGER.trace("Request parameter tour type id: " + tourTypeId);
        LOGGER.trace("Request parameter price: " + price);
        LOGGER.trace("Request parameter people amount: " + peopleAmount);
        LOGGER.trace("Request parameter stars: " + stars);



        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        TourDAO tourDAO = factory.tourDAO();

        List<Tour> tours = tourDAO.searchTours(tourTypeId, price, peopleAmount, stars);

        LOGGER.trace("Found in DB tours: " + tours);

        req.setAttribute("tours", tours);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.FORWARD, Path.PAGE_TOURS};
    }
}
