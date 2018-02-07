package web.action.hotel;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.CountryDAO;
import db.DAO.ResortDAO;
import db.DAOFactory;
import db.entity.Country;
import db.entity.Resort;
import db.postgresql.DAOImpl.CountryDAOImpl;
import db.postgresql.DAOImpl.ResortDAOImpl;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AddHotelPageAction implements Action{

    private static final Logger LOGGER = Logger.getLogger(AddHotelPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        CountryDAO countryDAO = factory.countryDAO();
        ResortDAO resortDAO = factory.resortDAO();

        List<Country> countries = countryDAO.getAllCountries();
        List<Resort> resorts = resortDAO.getAllResorts();

        Collections.sort(countries, new Country());
        Collections.sort(resorts, new Resort());

        LOGGER.trace("Found in DB countries: " + countries);
        LOGGER.trace("Found in DB countries: " + resorts);

        req.setAttribute("countries", countries);
        req.setAttribute("resorts", resorts);

        LOGGER.trace("Set request countries: " + countries);
        LOGGER.trace("Set request resorts: " + resorts);
        LOGGER.trace("Forward to " + Path.PAGE_ADD_HOTEL);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_ADD_HOTEL};
    }
}
