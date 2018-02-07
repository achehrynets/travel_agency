package web.action.hotel;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.entity.Hotel;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditHotelPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(EditHotelPageAction.class);

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

        req.setAttribute("hotel", hotel);

        LOGGER.trace("Set attribute hotel: " + hotel);

        return new String[]{AppConst.FORWARD, Path.PAGE_EDIT_HOTEL};
    }
}
