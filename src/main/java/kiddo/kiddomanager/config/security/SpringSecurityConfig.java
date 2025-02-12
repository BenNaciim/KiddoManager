package kiddo.kiddomanager.config.security;

import kiddo.kiddomanager.config.security.authentication.JWTAuthenticationFilter;
import kiddo.kiddomanager.config.security.authentication.JWTAuthorizationFilter;
import kiddo.kiddomanager.config.security.authentication.TokenGenerator;
import kiddo.kiddomanager.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static kiddo.kiddomanager.config.security.authentication.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authenticationService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager(),tokenGenerator);
        final JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl(SIGN_UP_URL);

        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // avoid creating session every must be
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login/**", "/register/parents","/register/employee","/refresh_token").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().fullyAuthenticated())
                .addFilter(jwtAuthenticationFilter)
                .addFilter(jwtAuthorizationFilter)
                .build();
    }

}
