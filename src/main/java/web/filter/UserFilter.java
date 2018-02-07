package web.filter;

import constant.ErrorMessages;
import constant.Path;
import db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "UserFilter",
        urlPatterns = "/*"
)
public class UserFilter implements Filter{

    private static final Logger LOGGER = Logger.getLogger(UserFilter.class);


    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("User filter init");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.isBlocked()) {
                servletRequest.setAttribute(ErrorMessages.MESSAGE, "Your account block by administrator");
                servletRequest.getRequestDispatcher(Path.PAGE_ERROR).forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        LOGGER.debug("User filter destroy");
    }
}
