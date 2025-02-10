package kiddo.kiddomanager.config.security.authentication;

public class SecurityConstants {

    public static final String SECRET = "nwogMfTvehBcvyOxvDtX";
    public static final long EXPIRATION_TIME = 1800000; // token expiration time 12 hours
    public static final long REFRESH_EXPIRATION_TIME = 604800000; // token expiration time 12 hours

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_UP_URL = "/login";
}
