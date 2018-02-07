package constant;

public class SQL {

    /**
     * Users query
     */
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM users WHERE role_id != 1";
    public static final String ADD_USER = "INSERT INTO users (login, password, email, surname, middle_name, "
            + "name, role_id, blocked,"
            + "passport_id, international_passport_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER_BLOCK_OR_UNBLOCK_BY_ID = "UPDATE users SET blocked = ? WHERE id = ?";

    /**
     * Country queries
     */
    public static final String GET_ALL_COUNTRIES = "SELECT * FROM countries";
    public static final String DELETE_COUNTRY_BY_ID = "DELETE FROM countries WHERE id = ?";
    public static final String GET_COUNTRY_BY_ID = "SELECT * FROM countries WHERE id = ?";
    public static final String GET_COUNTRY_BY_NAME = "SELECT * FROM countries WHERE name = ?";
    public static final String UPDATE_COUNTRY_BY_ID = "UPDATE countries SET name = ?, visa = ? WHERE id = ?";
    public static final String ADD_COUNTRY = "INSERT INTO countries (name, visa) VALUES (?, ?)";

    /**
     * Resort queries
     */
    public static final String GET_ALL_RESORTS = "SELECT * FROM resorts";
    public static final String UPDATE_RESORT = "UPDATE resorts SET name = ?, description = ? WHERE id = ?";
    public static final String ADD_RESORT = "INSERT INTO resorts (country_id, name, description, image_path) " +
            "VALUES (?, ?, ?, ?)";
    public static final String DELETE_RESORT_BY_ID = "DELETE FROM resorts WHERE id = ?";
    public static final String GET_RESORT_BY_ID = "SELECT * FROM resorts WHERE id = ?";
    public static final String GET_RESORT_BY_NAME = "SELECT * FROM resorts WHERE name = ?";

    /**
     * Hotel queries
     */
    public static final String GET_ALL_HOTELS = "SELECT hotels.id, hotels.name, countries.id AS country_id, " +
            "countries.name AS country_name, resorts.id AS resort_id, resorts.name as resort_name, hotels.stars, " +
            "hotels.price, hotels.description, hotels.image_path " +
            "FROM hotels INNER JOIN resorts ON hotels.resort_id = resorts.id " +
            "INNER JOIN countries ON hotels.country_id = countries.id";
    public static final String DELETE_HOTEL_BY_ID = "DELETE FROM hotels WHERE id = ?";
    public static final String ADD_HOTEL =
            "INSERT INTO hotels (name, resort_id, country_id, stars, description, price, image_path) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_HOTEL = "UPDATE hotels SET name = ?, stars = ?, price = ?,  description = ? WHERE id = ?";
    public static final String GET_HOTEL_BY_NAME =
            "SELECT hotels.id, hotels.name, countries.id AS country_id, " +
                    "countries.name AS country_name, resorts.id AS resort_id, resorts.name as resort_name, hotels.stars, " +
                    "hotels.price, hotels.description, hotels.image_path " +
                    "FROM hotels INNER JOIN resorts ON hotels.resort_id = resorts.id " +
                    "INNER JOIN countries ON hotels.country_id = countries.id WHERE hotels.name = ?";


    /**
     * Flight queries
     */
    public static final String ADD_FLIGHT =
            "INSERT INTO flights (transport_type_id, departure_point, departure_date, " +
                    "arrival_point, arrival_date, price) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Tour queries
     */
    public static final String ADD_TOUR =
            "INSERT INTO tours (name, hot_tour, place_quantity, amount_of_days, tour_date, total_price, " +
                    "tour_type_id, flight_id, hotel_id, resort_id, country_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_ALL_TOURS =
            "SELECT tours.id, tours.name, tours.hot_tour, tours.place_quantity, tours.amount_of_days, tours.tour_date, tours.total_price, tours.tour_type_id, " +
                    "countries.name AS country_name, countries.visa AS country_visa, " +
                    "resorts.name AS resort_name, resorts.description AS resort_description, resorts.image_path AS resort_image_path, " +
                    "hotels.name AS hotels_name, hotels.stars AS hotels_stars, hotels.description AS hotels_description, " +
                    "flights.arrival_point AS flight_arrival_point, flights.arrival_date AS flight_arrival_date, flights.transport_type_id AS transport_type_id, " +
                    "flights.travel_time AS travel_time, flights.departure_point AS flight_departure_point, flights.departure_date AS " +
                    "flight_departure_date FROM tours INNER JOIN countries ON tours.country_id = countries.id INNER JOIN resorts ON tours.resort_id = resorts.id " +
                    "INNER JOIN hotels ON tours.hotel_id = hotels.id INNER JOIN flights ON tours.flight_id = flights.id";
    public static final String SEARCH_TOUR =
            "SELECT tours.id, tours.name, tours.hot_tour, tours.place_quantity, tours.amount_of_days, tours.tour_date, tours.total_price, tours.tour_type_id, " +
                    "countries.name AS country_name, countries.visa AS country_visa, " +
                    "resorts.name AS resort_name, resorts.description AS resort_description, resorts.image_path AS resort_image_path, " +
                    "hotels.name AS hotels_name, hotels.stars AS hotels_stars, hotels.description AS hotels_description, " +
                    "flights.arrival_point AS flight_arrival_point, flights.arrival_date AS flight_arrival_date, flights.transport_type_id AS transport_type_id, " +
                    "flights.travel_time AS travel_time, flights.departure_point AS flight_departure_point, flights.departure_date AS " +
                    "flight_departure_date FROM tours INNER JOIN countries ON tours.country_id = countries.id INNER JOIN resorts ON tours.resort_id = resorts.id " +
                    "INNER JOIN hotels ON tours.hotel_id = hotels.id INNER JOIN flights ON tours.flight_id = flights.id " +
                    "WHERE tours.tour_type_id = ? AND tours.total_price < ? AND tours.place_quantity >= ? AND hotels.stars >= ?";
    public static final String GET_TOUR_BY_ID =
            "SELECT tours.id, tours.name, tours.hot_tour, tours.place_quantity, tours.amount_of_days, tours.tour_date, tours.total_price, tours.tour_type_id, " +
                    "countries.name AS country_name, countries.visa AS country_visa, " +
                    "resorts.name AS resort_name, resorts.description AS resort_description, resorts.image_path AS resort_image_path, " +
                    "hotels.name AS hotels_name, hotels.stars AS hotels_stars, hotels.description AS hotels_description, " +
                    "flights.arrival_point AS flight_arrival_point, flights.arrival_date AS flight_arrival_date, flights.transport_type_id AS transport_type_id, " +
                    "flights.travel_time AS travel_time, flights.departure_point AS flight_departure_point, flights.departure_date AS " +
                    "flight_departure_date FROM tours INNER JOIN countries ON tours.country_id = countries.id INNER JOIN resorts ON tours.resort_id = resorts.id " +
                    "INNER JOIN hotels ON tours.hotel_id = hotels.id INNER JOIN flights ON tours.flight_id = flights.id WHERE tours.id = ?";
    public static final String DELETE_TOUR_BY_ID = "DELETE FROM tours WHERE id = ?";
    public static final String UPDATE_PLACE_QUANTITY_ROW_BY_ID = "UPDATE tours SET place_quantity = ? WHERE id = ?";
    public static final String UPDATE_TOUR = "UPDATE tours SET name = ?, tour_type_id = ?, tour_date = ?, total_price = ?, amount_of_days = ?, " +
            "place_quantity = ? WHERE id = ?;";
    public static final String UPDATE_HOT_TOUR_ROW_BY_ID = "UPDATE tours SET hot_tour = true  WHERE id = ?;";

    /**
     * Order queries
     */
    public static final String ADD_ORDER = "INSERT INTO orders (total_price, people_amount, user_id, status_id, tour_id) VALUES (?, ?, ?, ?, ?)";
    public static final String GET_ALL_USER_ORDERS_BY_USER_ID =
            "SELECT orders.id, orders.people_amount, orders.total_price, users.id AS users_id, " +
                "users.login AS users_login, users.email AS users_email, users.surname AS users_surname, users.name AS users_name, users.phone AS users_phone, " +
                "tours.id AS tours_id, tours.name AS tours_name, statuses.id AS status_id " +
            "FROM orders " +
                "INNER JOIN users ON orders.user_id = users.id " +
                "INNER JOIN tours ON orders.tour_id = tours.id " +
                "INNER JOIN statuses ON orders.status_id = statuses.id " +
            "WHERE users.id = ?";
    public static final String GET_ALL_ORDERS =
            "SELECT orders.id, orders.people_amount, orders.total_price, users.id AS users_id, " +
                "users.login AS users_login, users.email AS users_email, users.surname AS users_surname, users.name AS users_name, users.phone AS users_phone, " +
                "tours.id AS tours_id, tours.name AS tours_name, statuses.id AS status_id " +
            "FROM orders " +
                "INNER JOIN users ON orders.user_id = users.id " +
                "INNER JOIN tours ON orders.tour_id = tours.id " +
                "INNER JOIN statuses ON orders.status_id = statuses.id";
    public static final String UPDATE_ORDER_STATUS_BY_ORDER_ID = "UPDATE orders SET status_id = ? WHERE id = ?";
    public static final String UPDATE_ORDER_PRICE = "UPDATE orders SET total_price = ? WHERE id = ?";
    public static final String GET_TOTAL_PRICE_OF_PAID_TOUR_FOR_CLIENTS =
            "SELECT users.login, users.email, SUM(orders.total_price) AS total_price, statuses.name" +
                    "  FROM users" +
                    "    INNER JOIN orders ON users.id = orders.user_id" +
                    "    INNER JOIN statuses ON orders.status_id = statuses.id" +
                    "  WHERE orders.status_id = 2" +
                    "  GROUP BY users.id, statuses.name;";


}