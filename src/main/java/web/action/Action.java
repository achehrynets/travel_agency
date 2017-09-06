package web.action;

import exception.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    String[] execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException,
            ApplicationException;

    default String getActionClass() {
        return getClass().getSimpleName();
    }

}
