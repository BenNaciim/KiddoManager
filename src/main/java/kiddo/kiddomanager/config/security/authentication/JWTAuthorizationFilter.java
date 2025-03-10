package kiddo.kiddomanager.config.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.SECRET;
import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.TOKEN_PREFIX;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final TokenGenerator tokenGenerator;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, TokenGenerator tokenGenerator) {
        super(authenticationManager);
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    // intercept any request if diff from the allowed url (see SpringSecurityConfig)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // check if header contain authorization and start with prefix BEARER
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (!StringUtils.hasText(header) || !header.startsWith(TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
            if (authentication == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or missing token");
                return;
            }


            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        } catch (TokenExpiredException e) {
            log.warn("Token expired, attempting refresh");
            try {
                tokenGenerator.refreshToken(request, response);
                log.info("Token refreshed successfully");
            } catch (Exception ex) {
                log.error("Token refresh failed, user must reauthenticate");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token expired, please login again");
            }
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT token", e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // retrieve the token and check if correct
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, Strings.EMPTY));
            String user = decodedJWT.getSubject();
            String role = decodedJWT.getClaim("role").asString();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, !StringUtils.hasText(role) ? Collections.emptyList() :
                        List.of(new SimpleGrantedAuthority(role)));
            }
            log.error("user is null");
            return null;
        }
        log.error("Token is null");
        return null;
    }
}
