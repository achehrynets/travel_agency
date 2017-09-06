package web.action;

import constant.AppConst;
import exception.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationAction implements Action{

    @Override
    public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ApplicationException {

        return new String[] {AppConst.REDIRECT, "index.jsp"};
    }
}
