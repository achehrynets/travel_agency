package constant;

public class ErrorMessages {

    private ErrorMessages(){}

    public static final String MESSAGES = "errorMessages";
    public static final String MESSAGE = "errorMessage";
    public static final String ERROR_CAN_NOT_SEND_MAIL = "Can not send mail";
    public static final String ERROR_CAN_NOT_OBTAIN_DATA_SOURCE = "Can not obtain data source";
    public static final String ERROR_CAN_NOT_OBTAIN_ALL_COUNTRIES = "Can not obtain all countries";
    public static final String ERROR_CAN_NOT_OBTAIN_ALL_RESORTS = "Can not obtain all resorts";
    public static final String ERROR_CAN_NOT_OBTAIN_ALL_USERS = "Can not obtain all users";
    public static final String ERROR_CAN_NOT_OBTAIN_COUNTRY_BY_NAME = "Can not obtain country by name: ";
    public static final String ERROR_CAN_NOT_UNBLOCK_USER = "Can not unblock user";
    public static final String ERROR_CAN_NOT_BLOCK_USER = "Can not block user";
    public static final String ERROR_CAN_NOT_UPDATE_COUNTRY = "Can not update country";
    public static final String ERROR_CAN_NOT_UPDATE_RESORT = "Can not update resort";
    public static final String ERROR_CAN_NOT_ADD_COUNTRY = "Can not add country";
    public static final String ERROR_CAN_NOT_ADD_RESORT = "Can not add resort";
    public static final String ERROR_CAN_NOT_REGISTER_USER = "Can not register user";
    public static final String ERROR_CAN_NOT_OBTAIN_COUNTRY_BY_ID = "Can not obtain country by id";
    public static final String ERROR_CAN_NOT_OBTAIN_RESORT_BY_ID = "Can not obtain resort by id";
    public static final String ERROR_CAN_NOT_OBTAIN_RESORT_BY_NAME = "Can not obtain resort by name";
    public static final String ERROR_CAN_NOT_OBTAIN_CONNECTION = "Can not obtain connection";
    public static final String ERROR_CAN_NOT_CLOSE_CONNECTION = "Can not close connection";
    public static final String ERROR_CAN_NOT_DELETE_COUNTRY = "Can not delete country";
    public static final String ERROR_CAN_NOT_DELETE_RESORT = "Can not delete resort";
    public static final String ERROR_CAN_NOT_CLOSE_STATEMENT = "Can not close statement";
    public static final String ERROR_CAN_NOT_CLOSE_RESULT_SET = "Can not close result set";
    public static final String ERROR_CAN_NOT_ROLLBACK_TRANSACTION = "Can not rollback transaction";
    public static final String ERROR_CAN_NOT_ENCRYPT_PASSWORD = "Can not encrypt password";
    public static final String ERROR_CAN_NOT_OBTAIN_USER_BY_LOGIN = "Can not obtain user by login";
    public static final String ERROR_EMPTY_LOGIN_OR_PASSWORD = "Login or password can not be empty";
    public static final String ERROR_EMPTY_INPUT = "Can not be empty input parameters";
    public static final String ERROR_INCORRECT_LOGIN = "Incorrect login";
    public static final String ERROR_INCORRECT_PASSWORD = "Incorrect password";

    public static final String ERROR_CAN_NOT_UPLOAD_IMAGE = "Can not upload image, something wrong!";
    public static final String ERROR_CAN_NOT_UPLOAD_IMAGE_NOT_MULTIPART_DATA
            = "Can not upload image, form not multipart/form-data!";

    //hotels
    public static final String ERROR_CAN_NOT_OBTAIN_ALL_HOTELS = "Can not obtain all hotels";
    public static final String ERROR_CAN_NOT_OBTAIN_HOTEL_BY_NAME = "Can not obtain hotel by name";
    public static final String ERROR_CAN_NOT_DELETE_HOTEL = "Can not delete hotel";
    public static final String ERROR_CAN_NOT_ADD_HOTEL = "Can not add hotel";
    public static final String ERROR_CAN_NOT_UPDATE_HOTEL = "Can not update hotel";

    //tours
    public static final String ERROR_CAN_NOT_ADD_TOUR = "Can not add tour";
    public static final String ERROR_CAN_NOT_OBTAIN_ALL_TOURS = "Can not obtain all tours";
    public static final String ERROR_CAN_NOT_OBTAIN_SEARCHED_TOURS = "Can not obtain searched tours";
    public static final String ERROR_CAN_NOT_OBTAIN_TOUR_BY_ID = "Can not obtain tour";
    public static final String ERROR_CAN_NOT_DELETE_TOUR = "Can not delete tour";
    public static final String ERROR_CAN_NOT_UPDATE_TOUR = "Can not update tour";
    public static final String ERROR_CAN_NOT_UPDATE_HOT_TOUR_ROW = "Can not update hot tour row";

    //orders
    public static final String ERROR_CAN_NOT_ADD_ORDER = "Can not add order";
    public static final String ERROR_CAN_NOT_OBTAIN_ALL_USER_ORDERS_BY_USER_ID = "Can not obtain orders by user id";
    public static final String ERROR_CAN_NOT_OBTAIN_ALL_ORDERS = "Can not obtain all orders";
    public static final String ERROR_CAN_NOT_UPDATE_ORDER_STATUS = "Can not update order status";
    public static final String ERROR_CAN_NOT_UPDATE_ORDER_PRICE = "Can not update order price";

    //Validation errors
    public static final String ERROR_LOGIN_FORMAT = "Login: from 3 up to 18 symbols";
    public static final String ERROR_PASSWORD_FORMAT = "Password: from 5 up to 32 symbols";
    public static final String ERROR_EMAIL_FORMAT = "Incorrect email format";
    public static final String ERROR_NAME_FORMAT = "Incorrect name format";
    public static final String ERROR_SURNAME_FORMAT = "Incorrect surname format";
    public static final String ERROR_MIDDLE_NAME_FORMAT = "Incorrect middle name format";
    public static final String ERROR_PASSPORT_FORMAT = "Incorrect passport id: 8 symbols";
    public static final String ERROR_INTERNATIONAL_PASSPORT_FORMAT = "Incorrect international Passport: 8 symbols";


}
