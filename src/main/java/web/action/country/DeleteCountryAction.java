package web.action.country;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.CountryDAO;
import db.DAOFactory;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteCountryAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(DeleteCountryAction.class);

    @Override
    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        CountryDAO countryDAO = factory.countryDAO();

        int id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");

        LOGGER.trace("Request parameter id: " + id);
        LOGGER.trace("Request parameter name: " + name);

        countryDAO.deleteCountryById(id);

        LOGGER.trace("Country with name: " + name + " successfully delete");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Country with name: " + name + " successfully delete");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_COUNTRIES_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Country with name: " + name + " successfully delete");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_COUNTRIES_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
