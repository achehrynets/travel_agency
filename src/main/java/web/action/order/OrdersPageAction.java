package web.action.order;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.OrderDAO;
import db.DAOFactory;
import db.entity.Order;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class OrdersPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(OrdersPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        OrderDAO orderDAO = factory.orderDAO();
        List<Order> orders = orderDAO.getAllOrders();

        Collections.sort(orders, new Order());

        LOGGER.trace("Found in DB orders: " + orders);

        req.setAttribute("orders", orders);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        List<Order> ordersTask = orderDAO.getTotalPriceOfPaidToursForClient();
        req.setAttribute("ordersTask", ordersTask);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        LOGGER.trace("Set request attribute: " + orders);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.FORWARD, Path.PAGE_ORDERS};
    }
}
