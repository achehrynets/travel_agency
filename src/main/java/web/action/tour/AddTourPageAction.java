package web.action.tour;

import constant.AppConst;
import constant.Path;
import exception.ApplicationException;
import web.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTourPageAction implements Action {

    @Override
    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {
        return new String[] {AppConst.FORWARD, Path.PAGE_ADD_TOUR_PAGE};
    }
}
