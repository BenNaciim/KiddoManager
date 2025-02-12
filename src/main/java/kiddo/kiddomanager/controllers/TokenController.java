package kiddo.kiddomanager.controllers;

import ch.qos.logback.core.util.StringUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kiddo.kiddomanager.config.security.authentication.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.SECRET;

@RestController
@RequestMapping("/refresh_token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenGenerator tokenGenerator;

    @GetMapping
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
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

        Map<String, String> generatedToken = tokenGenerator.generateToken(email, decodedJWT.getClaim("role").asString());

        tokenGenerator.addTokenToReponse(response, generatedToken);
    }
}
