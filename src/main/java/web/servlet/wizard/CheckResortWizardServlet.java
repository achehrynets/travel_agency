package web.servlet.wizard;

import constant.AppConst;
import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import db.DAO.ResortDAO;
import db.DAOFactory;
import db.entity.Resort;
import exception.DatabaseException;
import org.apache.log4j.Logger;
import util.FileReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet(
        "/wizard/check/resort"
)
public class CheckResortWizardServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CheckResortWizardServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        String resortName = req.getParameter("resortName");
        LOGGER.trace("Request parameter name: " + resortName);

        Resort resort = null;

        try {
            DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
            ResortDAO resortDAO = factory.resortDAO();
            resort = resortDAO.getResortByName(resortName);
        } catch (DatabaseException ex) {
            req.setAttribute(ErrorMessages.MESSAGE, ex.getMessage());
            req.getRequestDispatcher(Path.PAGE_ERROR).forward(req, resp);
        }

        LOGGER.trace("Found in DB country: " + resort);


        String path = "";
        String page = "";
        if (resort != null) {
            HttpSession session = req.getSession();
            session.setAttribute("resort", resort);

            path = getServletContext().getRealPath(Path.FILE_CHECK_HOTEL_WIZARD_PART);
            File file = new File(path);
            FileReader reader = new util.FileReader(file);
            page = reader.read();
        } else {
            path = getServletContext().getRealPath(Path.FILE_ADD_RESORT_WIZARD_PART);
            File file = new File(path);
            FileReader reader = new util.FileReader(file);
            page = reader.read();
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        resp.getWriter().write(page);
    }
}
