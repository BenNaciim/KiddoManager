package kiddo.kiddomanager.config.security.authentication;

public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // token expiration time 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGN_UP_URL = "/customer/login";
}
