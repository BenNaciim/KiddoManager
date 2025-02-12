package kiddo.kiddomanager.config.security.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kiddo.kiddomanager.config.security.Users;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

import static java.util.Collections.emptyList;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        //we retrieve the userName and password from the queryParameter if the authentication is success we call successfulAuthentication
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getParameter("userName"), request.getParameter("password"), emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) {
        String email = ((Users) authResult.getPrincipal()).getEmail();
        String role = CollectionUtils.isEmpty(authResult.getAuthorities()) ? Strings.EMPTY : authResult.getAuthorities().iterator().next().getAuthority();
        Map<String, String> generatedToken = tokenGenerator.generateToken(email, role);

        // Authorize the Authorization header to be exposed in the response headers to be used in the front end side
        tokenGenerator.addTokenToReponse(response, generatedToken);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication Failed : Bad Credentials");
    }
}
