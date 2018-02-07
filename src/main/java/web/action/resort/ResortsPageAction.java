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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ResortsPageAction implements Action {

    private static final Logger LOGGER = Logger.getLogger(ResortsPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        DAOFactory factory = DAOFactory.getDaoFactory(AppConst.USED_DB_NAME);
        ResortDAO resortDAO = factory.resortDAO();

        List<Resort> resorts = resortDAO.getAllResorts();
        for (Resort resort: resorts) {
            resort.setImagePath(req.getContextPath() + resort.getImagePath());
        }

        Collections.sort(resorts, new Resort());

        LOGGER.trace("Found in DB resorts: " + resorts);

        req.setAttribute("resorts", resorts);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_RESORTS};
    }
}
