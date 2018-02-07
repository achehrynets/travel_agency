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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddCountryAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(AddCountryAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        String name = req.getParameter("name");
        boolean visa = Boolean.valueOf(req.getParameter("visa"));

        LOGGER.trace("Request parameter name: " + name);
        LOGGER.trace("Request parameter visa: " + visa);

        Country country = new Country();
        country.setName(name);
        country.setVisa(visa);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        CountryDAO countryDAO = factory.countryDAO();

        countryDAO.addCountry(country);

        LOGGER.trace("Country with name: " + name + " successfully add");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Country with name: " + name + " successfully added");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_COUNTRIES_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Country with name: " + name + " successfully added");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_COUNTRIES_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
