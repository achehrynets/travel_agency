package constant;

public class RegularExpression {

    public static final String NAME_REGEX = "^[а-яА-яЁёA-Za-z]{3,32}$";
    public static final String PASSWORD_REGEX = "^[A-Za-z0-9_-]{5,32}$";
    public static final String LOGIN_REGEX = "^[a-z0-9_-]{3,18}$";
    public static final String EMAIL_REGEX = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|" +
            "(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final String PASSPORT_REGEX = "^[A-Z0-9]{8}$";
    public static final String POINT_NAME_REGEX = "^[А-ЯЁA-Z][А-Яа-яЁёA-Za-z_-]{3,32}$";
    public static final String TIME_REGEX = "^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$";
    public static final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](20)\\d\\d$";

}
