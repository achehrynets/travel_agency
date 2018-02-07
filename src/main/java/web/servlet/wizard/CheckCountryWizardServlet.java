package web.servlet.wizard;

import constant.AppConst;
import constant.ErrorMessages;
import constant.InfoMessages;
import constant.Path;
import db.DAO.CountryDAO;
import db.DAOFactory;
import db.entity.Country;
import util.FileReader;
import exception.DatabaseException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet(
        "/wizard/check/country"
)
public class CheckCountryWizardServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CheckCountryWizardServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        String name = req.getParameter("countryName");
        LOGGER.trace("Request parameter name: " + name);
        Country country = null;

        try {
            DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
            CountryDAO countryDAO = factory.countryDAO();
            country = countryDAO.getCountryByName(name);
        } catch (DatabaseException ex) {
            req.setAttribute(ErrorMessages.MESSAGE, ex.getMessage());
            req.getRequestDispatcher(Path.PAGE_ERROR).forward(req, resp);
        }

        LOGGER.trace("Found in DB country: " + country);


        String path = "";
        String page = "";
        if (country != null) {
            HttpSession session = req.getSession();
            session.setAttribute("country", country);

            path = getServletContext().getRealPath(Path.FILE_CHECK_RESORT_WIZARD_PART);
            File file = new File(path);
            FileReader reader = new util.FileReader(file);
            page = reader.read();
        } else {
            path = getServletContext().getRealPath(Path.FILE_ADD_COUNTRY_WIZARD_PART);
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
