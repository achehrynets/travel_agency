package web.action.hotel;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.HotelDAO;
import db.DAOFactory;
import db.entity.Country;
import db.entity.Hotel;
import db.entity.Resort;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold=1024*1024*2,
        maxFileSize=1024*1024*7,
        maxRequestSize=1024*1024*20
)
public class AddHotelAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(AddHotelAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        String name = req.getParameter("name");
        int stars = Integer.valueOf(req.getParameter("stars"));
        float price = Float.valueOf(req.getParameter("price"));
        String description = req.getParameter("description");
        long countryId = Long.valueOf(req.getParameter("countryId"));
        long resortId = Long.valueOf(req.getParameter("resortId"));

        LOGGER.trace("Request parameter name: " + name);
        LOGGER.trace("Request parameter stars: " + stars);
        LOGGER.trace("Request parameter price: " + price);
        LOGGER.trace("Request parameter description: " + description);
        LOGGER.trace("Request parameter country id: " + countryId);
        LOGGER.trace("Request parameter resort id: " + resortId);

        String imagePath = Path.HOTEL_DIR +
                File.separator + name +".png";

        LOGGER.trace("Image path: " + imagePath);

        for (Part part: req.getParts()) {
            part.write(imagePath);
        }

        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setStars(stars);
        hotel.setPrice(price);
        hotel.setDescription(description);
        hotel.setImagePath(imagePath);

        Country country = new Country();
        country.setId(countryId);
        hotel.setCountry(country);

        Resort resort = new Resort();
        resort.setId(resortId);
        hotel.setResort(resort);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        HotelDAO hotelDAO = factory.hotelDAO();

        hotelDAO.addHotel(hotel);

        LOGGER.trace("Hotel with name: " + hotel.getName() + " successfully add");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Hotel with name: " + hotel.getName() + " successfully add");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_HOTELS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Hotel with name: " + hotel.getName() + " successfully added");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_HOTELS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
