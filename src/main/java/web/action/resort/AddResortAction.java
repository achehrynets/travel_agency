package web.action.resort;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.DAO.ResortDAO;
import db.DAOFactory;
import db.entity.Resort;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold=1024*1024*2,
        maxFileSize=1024*1024*7,
        maxRequestSize=1024*1024*20
)
public class AddResortAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(AddResortAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        long countryId = Long.valueOf(req.getParameter("countryId"));

        LOGGER.trace("Request parameter name: " + name);
        LOGGER.trace("Request parameter description: " + description);
        LOGGER.trace("Request parameter country id: " + countryId);

        String imagePath = Path.RESORT_DIR +
                File.separator + name +".png";

        LOGGER.trace("Image path: " + imagePath);

        for (Part part: req.getParts()) {
            part.write(imagePath);
        }

        Resort resort = new Resort();
        resort.setName(name);
        resort.setDescription(description);
        resort.setImagePath(imagePath);
        resort.setCountryId(countryId);

        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        ResortDAO resortDAO = factory.resortDAO();

        resortDAO.addResort(resort);

        LOGGER.trace("Resort with name: " + resort.getName() + " successfully add");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Resort with name: " + resort.getName() + " successfully add");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_RESORTS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Resort with name: " + resort.getName() + " successfully added");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_RESORTS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.REDIRECT, AppConst.REDIRECT};
    }

}
