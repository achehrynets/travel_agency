package web.action.resort;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.CountryDAO;
import db.DAOFactory;
import db.entity.Country;
import db.postgresql.DAOImpl.CountryDAOImpl;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;
import web.action.country.AddCountryPageAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AddResortPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(AddCountryPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        CountryDAO countryDAO = factory.countryDAO();
        List<Country> countries = countryDAO.getAllCountries();

        Collections.sort(countries, new Country());

        LOGGER.trace("Found in DB countries: " + countries);

        req.setAttribute("countries", countries);

        LOGGER.trace("Set request countries: " + countries);
        LOGGER.trace("Forward to " + Path.PAGE_ADD_COUNTRY);
        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_ADD_RESORT};
    }
}
