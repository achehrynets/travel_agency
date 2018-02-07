package web.servlet.wizard;

import constant.AppConst;
import constant.InfoMessages;
import constant.Path;
import db.entity.*;
import db.service.TourService;
import db.service.postgersqlImpl.TourServiceImpl;
import exception.DatabaseException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(
        "/wizard/add/tour"
)
public class AddTourWizardServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddTourWizardServlet.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug(InfoMessages.INFO_ACTION_START);

        Flight flight = extractFlightFromRequest(req);
        LOGGER.trace("Request flight: " + flight);
        Tour tour = extractTourFromRequestAndSession(req, resp);
        LOGGER.trace("Request tour: " + tour);

        tour.setFlight(flight);

        try {
            addTour(tour);
        } catch (DatabaseException ex) {
            System.out.println("DB" + ex.getMessage());
            ex.printStackTrace();
        }

        LOGGER.trace("Tour with name: " + tour.getName() + " successfully add");

        HttpSession session = req.getSession();
        session.setAttribute(InfoMessages.INFO_MESSAGE, "Tour with name: " + tour.getName() + " successfully add");
        session.setAttribute(AppConst.FORWARD, Path.ACTION_TOURS_PAGE);

        LOGGER.trace("Set the session attribute: " + InfoMessages.INFO_MESSAGE
                + " --- " + "Tour with name: " + tour.getName() + " successfully added");
        LOGGER.trace("Set the session attribute: " + AppConst.FORWARD + " --- " + Path.ACTION_TOURS_PAGE);

        LOGGER.trace("Redirect to Post-Redirect-Get");

        LOGGER.debug(InfoMessages.INFO_ACTION_END);
        resp.sendRedirect("/redirect");
    }

    private Flight extractFlightFromRequest(HttpServletRequest req) throws IOException {
        int transportType = Integer.parseInt(req.getParameter("transportType"));
        String departurePoint = req.getParameter("departurePoint");
        String departureDate = req.getParameter("departureDate");
        String travelTime = req.getParameter("travelTime");
        String arrivalPoint = req.getParameter("arrivalPoint");
        String arrivalDate = req.getParameter("arrivalDate");
        float price = Float.parseFloat(req.getParameter("price"));


        Flight flight = new Flight();
        flight.setTransportTypeId(transportType);
        flight.setDeparturePoint(departurePoint);
        try {
            flight.setDepartureDate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(departureDate).getTime()));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            flight.setTravelTime(new Time(new SimpleDateFormat("HH:mm").parse(travelTime).getTime()));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        flight.setArrivalPoint(arrivalPoint);
        try {
            flight.setArrivalDate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(arrivalDate).getTime()));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        flight.setPrice(price);

        return flight;
    }

    private Tour extractTourFromRequestAndSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        int tourTypeId = Integer.parseInt(req.getParameter("tourType"));
        Date tourDate = null;
        int amountOfDays = Integer.parseInt(req.getParameter("amountOfDays"));
        int placeQuantity = Integer.parseInt(req.getParameter("placeQuantity"));
        float tourPrice = Float.parseFloat(req.getParameter("tourPrice"));
        try {
            tourDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("tourDate"));
        } catch (ParseException ex) {
            resp.getWriter().write("Invalid tour date format: yyyy-MM-dd");
        }

        Tour tour = new Tour();
        tour.setName(name);
        tour.setTourTypeId(tourTypeId);
        tour.setTourDate(tourDate);
        tour.setTotalPrice(tourPrice);
        tour.setAmountOfDays(amountOfDays);
        tour.setPlaceQuantity(placeQuantity);

        HttpSession session = req.getSession();
        Country country = (Country) session.getAttribute("country");
        Resort resort = (Resort) session.getAttribute("resort");
        Hotel hotel = (Hotel) session.getAttribute("hotel");
        tour.setCountry(country);
        tour.setResort(resort);
        tour.setHotel(hotel);

        return tour;
    }

    private void addTour(Tour tour) throws DatabaseException {
        if (tour.getCountry().getId() == null && tour.getResort().getId() == null && tour.getHotel().getId() == null) {
                TourService tourService = new TourServiceImpl();
                tourService.addTour(tour);
        } else if (tour.getCountry().getId() != null && tour.getResort().getId() == null &&
                tour.getHotel().getId() == null) {

                TourService tourService = new TourServiceImpl();
                tourService.addTourWhenCountryExist(tour);
        } else if (tour.getCountry().getId() != null && tour.getResort().getId() != null &&
                tour.getHotel().getId() == null) {

                TourService tourService = new TourServiceImpl();
                tourService.addTourWhenCountryAndResortExist(tour);
        } else if (tour.getCountry().getId() != null && tour.getResort().getId() != null &&
                tour.getHotel().getId() != null) {

                TourService tourService = new TourServiceImpl();
                tourService.addTourWhenCountryAndResortAndHotelExist(tour);
        }
    }
}
