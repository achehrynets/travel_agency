package web.action;

import constant.AppConst;
import constant.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewRegistrationPageAction implements Action {

    @Override
    public String[] execute(HttpServletRequest req, HttpServletResponse resp) {
        return new String[] {AppConst.FORWARD, Path.REGISTRATION_PAGE};
    }
}
