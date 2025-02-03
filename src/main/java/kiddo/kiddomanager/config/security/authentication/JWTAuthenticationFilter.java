package kiddo.kiddomanager.config.security.authentication;

import com.auth0.jwt.JWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kiddo.kiddomanager.config.security.Users;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static java.util.Collections.emptyList;
import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.EXPIRATION_TIME;
import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.SECRET;
import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        //we retrieve the userName and password from the queryParameter if the authentication is success we call successfulAuthentication
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getParameter("userName"), request.getParameter("password"), emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {
        // Create the token from the secret key
        String token = JWT.create()
                .withSubject(((Users) authResult.getPrincipal()).getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        // Authorize the Authorization header to be exposed in the response headers to be used in the front end side
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        // Put the token in the Authorization header
        response.addHeader(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token);
        // Add a msg in the response body
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication Failed : Bad Credentials");
    }
}
