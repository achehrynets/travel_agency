package web.action.country;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.CountryDAO;
import db.DAOFactory;
import db.DBName;
import db.entity.Country;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class EditCountryAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(EditCountryAction.class);

    @Override
    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        DAOFactory factory = DAOFactory.getDaoFactory(DBName.POSTGRESQL);
        CountryDAO countryDAO = factory.countryDAO();

        int id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        boolean visa = Boolean.valueOf(req.getParameter("visa"));

        Country country = new Country();
        country.setId(id);
        country.setName(name);
        country.setVisa(visa);

        LOGGER.trace("Request params: " + country);

        countryDAO.updateCountryByID(country);

        LOGGER.trace("Country with id: " + country.getId() + " was updated");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Country was updated");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_COUNTRIES_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE + " --- " + "Country was updated");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_COUNTRIES_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
