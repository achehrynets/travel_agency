package web.action.country;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.CountryDAO;
import db.DAOFactory;
import db.entity.Country;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CountriesPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(CountriesPageAction.class);

    @Override
    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        CountryDAO countryDAO = factory.countryDAO();

        List<Country> countries = countryDAO.getAllCountries();
        Collections.sort(countries, new Country());

        LOGGER.trace("Found in DB countries: " + countries);

        req.setAttribute("countries", countries);
        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_COUNTRIES};
    }
}
