package web.action.order;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.OrderDAO;
import db.DAOFactory;
import db.entity.Order;
import db.entity.Status;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import util.MailSender;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeOrderStatusAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(ChangeOrderStatusAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        long statusId = Long.parseLong(req.getParameter("status"));
        long orderId = Long.parseLong(req.getParameter("order_id"));
        String userEmail = req.getParameter("user_email");

        LOGGER.trace("Request parameter status id: " + statusId);
        LOGGER.trace("Request parameter order id: " + orderId);
        LOGGER.trace("Request parameter user email: " + userEmail);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        OrderDAO orderDAO = factory.orderDAO();

        orderDAO.updateOrderStatus(orderId, statusId);
        MailSender sender = new MailSender(req);
        String subject = "Dear customer, the status of your order has been changed";
        Order order = new Order();
        order.setStatusId((int)statusId);
        String text =
                "Dear customer, the status of your order has been changed to " + Status.getSatus(order).getName() + System.lineSeparator() +
                    "Thank for your order";
        sender.send(subject, text, userEmail);

        LOGGER.trace("Order status successfully update");
        LOGGER.trace("Mail successfully send");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Order status successfully update");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_ORDERS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Order status successfully update");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_ORDERS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
