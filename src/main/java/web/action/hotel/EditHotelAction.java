package web.action.hotel;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.HotelDAO;
import db.DAOFactory;
import db.entity.Hotel;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditHotelAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(EditHotelAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        Hotel hotel = new Hotel();
        long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        int stars = Integer.valueOf(req.getParameter("stars"));
        float price = Float.valueOf(req.getParameter("price"));
        String description = req.getParameter("description");

        LOGGER.trace("Request parameter id: " + id);
        LOGGER.trace("Request parameter name: " + name);
        LOGGER.trace("Request parameter stars: " + stars);
        LOGGER.trace("Request parameter price: " + price);
        LOGGER.trace("Request parameter description: " + description);

        hotel.setId(id);
        hotel.setName(name);
        hotel.setStars(stars);
        hotel.setPrice(price);
        hotel.setDescription(description);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        HotelDAO hotelDAO = factory.hotelDAO();
        hotelDAO.updateHotel(hotel);

        LOGGER.trace("Hotel with id " + hotel.getId() + " was update");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Hotel was update");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_HOTELS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE + " --- " + "Hotel was updated");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_HOTELS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
