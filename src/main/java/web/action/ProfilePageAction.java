package web.action;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.OrderDAO;
import db.DAOFactory;
import db.entity.Order;
import db.entity.User;
import exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ProfilePageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(ProfilePageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);


        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        LOGGER.trace("Found in session user: " + user);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        OrderDAO orderDAO = factory.orderDAO();
        List<Order> orders = orderDAO.getAllUserOrdersByUserId(user.getId());

        Collections.sort(orders, new Order());

        LOGGER.trace("Found in DB orders: " + orders);

        req.setAttribute("orders", orders);

        LOGGER.trace("Set request attribute orders: " + orders);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[] {AppConst.FORWARD, Path.PAGE_PROFILE};
    }
}
