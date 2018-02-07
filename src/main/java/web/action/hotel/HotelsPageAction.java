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
import java.io.IOException;
import java.util.List;

public class HotelsPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(HotelsPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        HotelDAO hotelDAO = factory.hotelDAO();

        List<Hotel> hotels = hotelDAO.getAllHotels();

        LOGGER.trace("Found in DB: " + hotels);

        req.setAttribute("hotels", hotels);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_HOTELS};
    }
}
