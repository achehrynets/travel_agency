package web.action.tour;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.TourDAO;
import db.DAOFactory;
import db.entity.Tour;
import db.entity.TourType;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditTourAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(EditTourAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        long tour_id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        int tourType = Integer.parseInt(req.getParameter("tourType"));
        Date tourDate = null;
        try {
            tourDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("tourDate"));
        } catch (ParseException e) {
            System.out.println("Cant parse date");
        }
        float tourPrice = Float.parseFloat(req.getParameter("tourPrice"));
        int amountOfDays = Integer.parseInt(req.getParameter("amountOfDays"));
        int placeQuantity = Integer.parseInt(req.getParameter("placeQuantity"));

        Tour tour = new Tour();
        tour.setTourTypeId(tourType);

        LOGGER.trace("Request parameter tour id: " + tour_id);
        LOGGER.trace("Request parameter name: " + name);
        LOGGER.trace("Request parameter tour type: " + TourType.getTourType(tour).getName());
        LOGGER.trace("Request parameter tour date: " + tourDate);
        LOGGER.trace("Request parameter tour price: " + tourPrice);
        LOGGER.trace("Request parameter amount of days: " + amountOfDays);
        LOGGER.trace("Request parameter place quantity: " + placeQuantity);

        tour.setId(tour_id);
        tour.setName(name);
        tour.setTourDate(tourDate);
        tour.setTotalPrice(tourPrice);
        tour.setAmountOfDays(amountOfDays);
        tour.setPlaceQuantity(placeQuantity);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        TourDAO tourDAO = factory.tourDAO();
        tourDAO.updateTour(tour);

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
