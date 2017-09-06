package web;

import constant.AppConst;
import constant.ErrorMessages;
import constant.Path;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;
import web.action.ActionContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "FrontController",
        urlPatterns = "/controller"
)
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FrontController.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }


    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("Controller start");

        String actionName = req.getParameter("action");
        Action action = ActionContainer.getWebAction(actionName);

        LOGGER.trace("Request action:  " + actionName);
        LOGGER.trace("Obtained action:  " + action.getActionClass());

        String[] responsePage = new String[2];
        try {
            responsePage = action.execute(req, resp);
        } catch (ApplicationException ex) {
            req.setAttribute(ErrorMessages.ERROR_MESSAGE, ex.getMessage());
            responsePage[1] = ex.getForward();
        }
        LOGGER.trace("Forward address --> " + responsePage[1]);
        LOGGER.debug("FrontController finished, now go to forward address --> " + responsePage[1]);
        LOGGER.debug("Controller end");
        if (AppConst.REDIRECT.equals(responsePage[1])){
            resp.sendRedirect(responsePage[1]);
        } else {
            req.getRequestDispatcher(responsePage[1]).forward(req, resp);
        }
    }

}
