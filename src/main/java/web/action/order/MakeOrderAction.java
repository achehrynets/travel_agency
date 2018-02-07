package web.action.order;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.entity.Order;
import db.entity.Tour;
import db.entity.User;
import db.service.OrderService;
import db.service.postgersqlImpl.OrderServiceImpl;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MakeOrderAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(MakeOrderAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        int tourId = Integer.parseInt(req.getParameter("tour_id"));
        int placeQuantity = Integer.parseInt(req.getParameter("place_quantity"));
        float tourPrice = Float.parseFloat(req.getParameter("tour_price"));
        int peopleAmount = Integer.parseInt(req.getParameter("people_amount"));

        LOGGER.trace("Request parameter tour id: " + tourId);
        LOGGER.trace("Request parameter tour placeQuantity: " + placeQuantity);
        LOGGER.trace("Request parameter tour tourPrice: " + tourPrice);
        LOGGER.trace("Request parameter tour peopleAmount: " + peopleAmount);

        Order order = new Order();
        User user = (User) req.getSession().getAttribute("user");
        order.setUser(user);
        Tour tour = new Tour();
        tour.setId(tourId);
        tour.setPlaceQuantity(placeQuantity);
        order.setTour(tour);
        order.setPeopleAmount(peopleAmount);
        order.setStatusId(1);
        order.setTotalPrice(peopleAmount * tourPrice);

        OrderService orderService = new OrderServiceImpl();
        orderService.addOrderAndUpdateTour(order);

        LOGGER.trace("Order successfully add");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Order successfully add");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_PROFILE_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Order successfully add");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_PROFILE_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");


        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
