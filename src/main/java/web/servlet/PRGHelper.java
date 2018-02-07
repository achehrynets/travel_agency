package web.servlet;

import constant.AppConst;
import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "PRGHelper",
        urlPatterns = "/redirect"
)
public class PRGHelper extends HttpServlet{

    private static final Logger LOGGER = Logger.getLogger(PRGHelper.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Post-Redirect-Get Helper Start");
        HttpSession session = req.getSession();

        String message = (String) session.getAttribute(InfoMessages.INFO_MESSAGE);
        String forward = (String) session.getAttribute(AppConst.FORWARD);

        LOGGER.trace("Forward to " + forward + " with message: " + message);

        req.removeAttribute(InfoMessages.INFO_MESSAGE);
        req.setAttribute(InfoMessages.INFO_MESSAGE, message);

        LOGGER.debug("Post-Redirect-Get Helper End");
        req.getRequestDispatcher(forward).forward(req, resp);
    }

}
