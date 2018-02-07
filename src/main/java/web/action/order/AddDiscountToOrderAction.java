package web.action.order;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.OrderDAO;
import db.DAOFactory;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import util.MailSender;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddDiscountToOrderAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(AddDiscountToOrderAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        long orderId = Long.parseLong(req.getParameter("order_id"));
        int discount = Integer.parseInt(req.getParameter("discount"));
        float price = Float.parseFloat(req.getParameter("price"));
        String userEmail = req.getParameter("user_email");

        LOGGER.trace("Request parameter order id: " + orderId);
        LOGGER.trace("Request parameter discount: " + discount);
        LOGGER.trace("Request parameter price: " + price);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        OrderDAO orderDAO = factory.orderDAO();
        float discountValue = price * discount / 100;
        price = price - discountValue;

        LOGGER.trace("New price for order: " + price);
        orderDAO.updateOrderPrice(orderId, price);

        MailSender sender = new MailSender(req);
        String subject = "Dear customer, for your order has been approved discount";
        String text = "Dear customer for your order, a discount of $" + discountValue + " was approved. " + System.lineSeparator() +
                "Thanks for your order.";

        sender.send(subject, text, userEmail);

        LOGGER.trace("Mail successfully send");
        LOGGER.trace("Order price successfully update");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Order price successfully update");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_ORDERS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Order price successfully update");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_ORDERS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.REDIRECT, AppConst.REDIRECT};
    }
}
