package kiddo.kiddomanager.config.security.authentication;

import ch.qos.logback.core.util.StringUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.EXPIRATION_TIME;
import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.REFRESH_EXPIRATION_TIME;
import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.SECRET;
import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.TOKEN_PREFIX;

@Component
public class TokenGenerator {

    public Map<String,String> generateToken(String email, String role){
        String token ;
        String refreshToken ;
        if(StringUtil.isNullOrEmpty(role)) {
            token = JWT.create()
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(HMAC512(SECRET.getBytes()));

            // Création du JWT de refresh
            refreshToken = JWT.create()
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                    .sign(HMAC512(SECRET.getBytes()));

        }
        else {
            // Create the token from the secret key
            token = JWT.create()
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //30 minutes
                    .withClaim("role", role)
                    .sign(HMAC512(SECRET.getBytes()));

            refreshToken = JWT.create()
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis()+REFRESH_EXPIRATION_TIME)) // 7 Days
                    .withClaim("role", role)
                    .sign(HMAC512(SECRET.getBytes()));
        }
        // return both tokens in a Map
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token", token);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

      void addTokenToReponse(HttpServletResponse response, Map<String, String> generatedToken) {
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        // Put the token in the Authorization header
        response.addHeader(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + generatedToken.get("token"));
        // Store the refresh token in a cookie
        Cookie refreshCookie = new Cookie("refresh_token", generatedToken.get("refreshToken"));
        refreshCookie.setHttpOnly(true); // prevent JS from accessing the cookie
        refreshCookie.setSecure(true); // Only send the cookie over HTTPS
        refreshCookie.setPath("/"); // The cookie is visible to all the paths
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);
        refreshCookie.setAttribute("SameSite", "Strict"); // The cookie is sent only if the request is from the same site

        response.addCookie(refreshCookie);
        // Add a msg in the response body
        response.setStatus(HttpServletResponse.SC_OK);
    }

     void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie refreshTokenCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refresh_token"))
                .findFirst()
                .orElseThrow(() -> new InvalidCookieException("No refresh token found"));

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(refreshTokenCookie.getValue());
        String email = decodedJWT.getSubject();
        if(StringUtil.isNullOrEmpty(email)) {
            throw new InvalidCookieException("Email Invalid");
        }

        Map<String, String> generatedToken = generateToken(email, decodedJWT.getClaim("role").asString());

        addTokenToReponse(response, generatedToken);
    }
}
