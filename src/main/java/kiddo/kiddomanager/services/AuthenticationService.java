package kiddo.kiddomanager.services;

import kiddo.kiddomanager.config.security.Users;
import kiddo.kiddomanager.models.entities.ParentsEntity;
import kiddo.kiddomanager.models.entities.PersonalEntity;
import kiddo.kiddomanager.repositories.ParentsRepository;
import kiddo.kiddomanager.repositories.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final ParentsRepository parentsRepository;
    private final PersonalRepository personalRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ParentsEntity usersByEmail = parentsRepository.findParentsEntityByEmail(email);

        if (Objects.isNull(usersByEmail)) {
            return loadFromPersonalTable(email);
        } else {
            return new Users(
                    usersByEmail.getEmail(),
                    usersByEmail.getPassword(),
                    Collections.emptyList(),
                    usersByEmail.getEmail()
            );

        }
    }

    private Users loadFromPersonalTable(String email) {
        PersonalEntity usersByEmail = personalRepository.findPersonalEntityByEmail(email);

        if (Objects.isNull(usersByEmail)) {
            throw new UsernameNotFoundException("User not found");
        }
        else {
            return new Users(
                    usersByEmail.getEmail(),
                    usersByEmail.getPassword(),
                    Collections.emptyList(),
                    usersByEmail.getEmail()
            );
        }
    }
}
