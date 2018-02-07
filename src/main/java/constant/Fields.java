package constant;

/**
 *
 * Current class contains fields from DB tables.
 *
 * @author A.Chegrinets
 */
public class Fields {

    private Fields() {}
    /**
     * For all entity
     */
    public static final String ENTITY_ID = "id";
    public static final String NAME = "name";

    /**
     * For users entity
     */
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_SURNAME = "surname";
    public static final String USER_MIDDLE_NAME = "middle_name";
    public static final String USER_PHONE = "phone";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_BLOCKED = "blocked";
    public static final String USER_PASSPORT_ID = "passport_id";
    public static final String USER_INTERNATIONAL_PASSPORT_ID = "international_passport_id";

    /**
     * For countries entity
     */
    public static final String COUNTRY_VISA = "visa";

}
