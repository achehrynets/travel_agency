package constant;

public class Path {

    /**
     * Paths
     */
    public static final String PAGE_MAIN = "/";
    public static final String PAGE_ERROR = "/errorPage.jsp";
    public static final String PAGE_REGISTRATION = "/WEB-INF/jsp/registration.jsp";
    public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE_USERS = "/WEB-INF/jsp/admin/blockUsers.jsp";
    public static final String PAGE_PROFILE = "/WEB-INF/jsp/profile.jsp";


    public static final String PAGE_ADD_TOUR_PAGE = "/WEB-INF/jsp/admin/tour/addTour.jsp";


    public static final String PAGE_HOTELS = "/WEB-INF/jsp/hotels.jsp";
    public static final String PAGE_ADD_HOTEL = "/WEB-INF/jsp/admin/hotel/addHotel.jsp";
    public static final String PAGE_EDIT_HOTEL = "/WEB-INF/jsp/admin/hotel/editHotel.jsp";

    public static final String PAGE_ADD_COUNTRY = "/WEB-INF/jsp/admin/country/addCountry.jsp";
    public static final String PAGE_EDIT_COUNTRIES = "/WEB-INF/jsp/admin/country/editCountry.jsp";
    public static final String PAGE_COUNTRIES = "/WEB-INF/jsp/admin/country/countries.jsp";


    public static final String PAGE_ADD_RESORT = "/WEB-INF/jsp/admin/resort/addResort.jsp";
    public static final String PAGE_RESORTS = "/WEB-INF/jsp/resorts.jsp";
    public static final String PAGE_EDIT_RESORT = "/WEB-INF/jsp/admin/resort/editResort.jsp";

    public static final String PAGE_TOURS = "/WEB-INF/jsp/tours.jsp";
    public static final String PAGE_EDIT_TOUR = "/WEB-INF/jsp/admin/tour/editTourPage.jsp";
    public static final String PAGE_INFO_ABOUT_TOUR = "/WEB-INF/jsp/tourInfo.jsp";



    public static final String PAGE_MAKE_ORDER = "/WEB-INF/jsp/makeOrderPage.jsp";
    public static final String PAGE_ORDERS = "/WEB-INF/jsp/manager/orders.jsp";


    /**
     * Actions
     */
    public static final String ACTION_COUNTRIES_PAGE = "controller?action=countriesPage";
    public static final String ACTION_USERS_PAGE = "controller?action=usersPage";
    public static final String ACTION_RESORTS_PAGE = "controller?action=resortsPage";
    public static final String ACTION_HOTELS_PAGE = "controller?action=hotelsPage";
    public static final String ACTION_TOURS_PAGE = "controller?action=toursPage";
    public static final String ACTION_PROFILE_PAGE = "controller?action=profilePage";
    public static final String ACTION_ORDERS_PAGE = "controller?action=ordersPage";



    /**
     * Path to wizard jsp
     */
    public static final String FILE_ADD_COUNTRY_WIZARD_PART = "WEB-INF/jsp/wizard/addCountryWizard.jsp";
    public static final String FILE_ADD_RESORT_WIZARD_PART = "WEB-INF/jsp/wizard/addResortWizard.jsp";
    public static final String FILE_ADD_HOTEL_WIZARD_PART = "WEB-INF/jsp/wizard/addHotelWizard.jsp";
    public static final String FILE_CHECK_RESORT_WIZARD_PART = "WEB-INF/jsp/wizard/CheckResort.jsp";
    public static final String FILE_CHECK_HOTEL_WIZARD_PART = "WEB-INF/jsp/wizard/checkHotel.jsp";
    public static final String FILE_ADD_TOUR_WIZARD_PART = "WEB-INF/jsp/wizard/addTourWizard.jsp";


    /**
     * Path to image directory
     */
    public static final String RESORT_DIR =
            "/home/jo1nsaint/projects/javaProjects/SummaryTask4/src/main/webapp/WEB-INF/images/resorts";
    public static final String HOTEL_DIR =
            "/home/jo1nsaint/projects/javaProjects/SummaryTask4/src/main/webapp/WEB-INF/images/hotels";
}
