package web.servlet.wizard;

import constant.InfoMessages;
import constant.Path;
import db.entity.Country;
import util.FileReader;
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
        "/wizard/add/country"
)
public class AddCountryWizardServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddCountryWizardServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        String name = req.getParameter("countryName");
        boolean visa = Boolean.valueOf(req.getParameter("visa"));
        LOGGER.trace("Request parameter name: " + name);
        LOGGER.trace("Request parameter visa: " + visa);
        Country country = new Country();
        country.setName(name);
        country.setVisa(visa);

        HttpSession session = req.getSession();
        session.setAttribute("country", country);
        LOGGER.trace("Country with name: " + name + " successfully added to session");

        String path = getServletContext().getRealPath(Path.FILE_ADD_RESORT_WIZARD_PART);
        File file = new File(path);
        FileReader reader = new util.FileReader(file);
        String page = reader.read();

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        resp.getWriter().write(page);
    }

}
