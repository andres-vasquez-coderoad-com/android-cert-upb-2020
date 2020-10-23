package bo.com.emprendeya.utils;

public class Constants {
    //General
    public static final int ERROR_EMPTY_VALUES = 1000;
    public static final int ERROR_NO_CONNECTION = 1001;

    //Login
    public static final int ERROR_LOGIN = 2000;
    public static final int ERROR_INVALID_EMAIL = 2001;
    public static final int ERROR_REGISTER = 2002;
    public static final int ERROR_REGISTER_EMAIL_ALREADY_EXISTS = 2003;
    public static final int ERROR_LOGIN_GOOGLE = 2004;
    public static final int ERROR_REGISTER_DB = 2005;

    //Server
    public static final int ERROR_SERVER = 3000;

    public static final String KEY_UUID = "uuid";
    public static final String KEY_DISPLAY_NAME = "displayName";
    public static final String KEY_STARTUP_SELECTED = "startupSelected";

    //Startup Lists
    public static final int CAROUSEL_COUNT = 5;

    //Api
    public static final String BASE_URL = "https://firebasestorage.googleapis.com/v0/b/emprende-ya-2cd09.appspot.com/o/";
    public static final String RESOURCE_STARTUPS = "json%2Fstartups.json";
    public static final String QUERY_PARAM_ALT = "media";

    //Firebase
    public static final String FIREBASE_PATH_USERS = "/users";
}
