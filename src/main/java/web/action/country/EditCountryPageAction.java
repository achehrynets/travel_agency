package web.action.country;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.entity.Country;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCountryPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(CountriesPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        int id = Integer.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");

        Country country = new Country();
        country.setId(id);
        country.setName(name);
        req.setAttribute("country", country);

        LOGGER.trace("Set request attribute id: " + id + " and name: " + name);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_EDIT_COUNTRIES};
    }
}
