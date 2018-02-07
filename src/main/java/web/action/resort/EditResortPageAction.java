package web.action.resort;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.entity.Resort;
import exception.ApplicationException;
import org.apache.log4j.Logger;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditResortPageAction implements Action{

    private static final Logger LOGGER = Logger.getLogger(ResortsPageAction.class);

    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);
        Resort resort = new Resort();
        long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        resort.setId(id);
        resort.setName(name);
        resort.setDescription(description);

        LOGGER.trace("Request parameter id: " + id);
        LOGGER.trace("Request parameter name: " + name);
        LOGGER.trace("Request parameter description: " + description);

        req.setAttribute("resort", resort);

        LOGGER.trace("Set attribute resort: " + resort);

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        return new String[]{AppConst.FORWARD, Path.PAGE_EDIT_RESORT};
    }
}
