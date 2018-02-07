package web.filter;

import org.apache.log4j.Logger;
import db.entity.Role;
import constant.Path;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter(
        displayName = "ActionAccessFilter",
        urlPatterns = "/controller",
        initParams = {
            @WebInitParam(name = "admin", value = "usersPage blockOrUnblockUser countriesPage deleteCountry editCountry editCountyPage addCountryPage addCountry deleteResort addResortPage addResort editResortPage editResort deleteHotel addHotelPage addHotel editHotelPage editHotel deleteTour addTourPage editTourPage editTour setHotTour ordersPage changeOrderStatus addDiscountToOrder"),
            @WebInitParam(name = "manager", value = "addDiscountToOrder changeOrderStatus ordersPage setHotTour"),
            @WebInitParam(name = "client", value = "orderPage addOrder"),
            @WebInitParam(name = "common", value = "logout noCommand profilePage"),
            @WebInitParam(name = "out-of-control", value = "login registration registrationPage toursPage tourInfoPage resortsPage hotelsPage searchTour")
        }
)
public class ActionAccessFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(ActionAccessFilter.class);

    private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
    private List<String> commons = new ArrayList<String>();
    private List<String> outOfControl = new ArrayList<String>();

    public void destroy() {
        LOGGER.debug("Filter destruction starts");

        LOGGER.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("Filter starts");

        if (accessAllowed(request)) {
            LOGGER.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessage);
            LOGGER.trace("Set the request attribute: errorMessage --> " + errorMessage);

            request.getRequestDispatcher(Path.PAGE_ERROR)
                    .forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String actionName = request.getParameter("action");
        if (actionName == null || actionName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(actionName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role)session.getAttribute("role");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(actionName)
                || commons.contains(actionName);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOGGER.debug("Filter initialization starts");

        accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Role.MANAGER, asList(fConfig.getInitParameter("manager")));
        accessMap.put(Role.CLIENT, asList(fConfig.getInitParameter("client")));
        LOGGER.trace("Access map --> " + accessMap);

        commons = asList(fConfig.getInitParameter("common"));
        LOGGER.trace("Common actions --> " + commons);

        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOGGER.trace("Out of control actions --> " + outOfControl);

        LOGGER.debug("Filter initialization finished");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str
     *            parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
