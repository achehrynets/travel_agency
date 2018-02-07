package web.action.hotel;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.HotelDAO;
import db.DAOFactory;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


public class DeleteHotelAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(DeleteHotelAction.class);

    @Override
    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        long id = Long.valueOf(req.getParameter("id"));
        String imagePath = req.getParameter("imagePath");

        LOGGER.trace("Request parameter id: " + id);
        LOGGER.trace("Request parameter imagePath: " + imagePath);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        HotelDAO hotelDAO = factory.hotelDAO();

        hotelDAO.deleteHotelById(id);

        LOGGER.trace("Hotel successfully delete");

        File file = new File(imagePath);

        LOGGER.trace("File delete: " + file.delete());

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Hotel successfully delete");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_HOTELS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Hotel successfully delete");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_HOTELS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
