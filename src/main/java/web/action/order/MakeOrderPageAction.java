package web.action.order;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeOrderPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(MakeOrderPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int placeQuantity = Integer.parseInt(req.getParameter("placeQuantity"));
        float price = Float.parseFloat(req.getParameter("price"));

        LOGGER.trace("Request parameter id: " + id);
        LOGGER.trace("Request parameter name: " + name);
        LOGGER.trace("Request parameter place quantity: " + placeQuantity);
        LOGGER.trace("Request parameter price: " + price);

        req.setAttribute("id", id);
        req.setAttribute("name", name);
        req.setAttribute("placeQuantity", placeQuantity);
        req.setAttribute("price", price);


        LOGGER.trace("Set forward attribute id: " + id);
        LOGGER.trace("Set forward attribute name: " + name);
        LOGGER.trace("Set forward attribute place quantity: " + placeQuantity);
        LOGGER.trace("Set forward attribute price: " + price);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_MAKE_ORDER};
    }
}
