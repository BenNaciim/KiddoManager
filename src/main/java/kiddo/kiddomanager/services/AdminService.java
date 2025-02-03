package kiddo.kiddomanager.services;

import kiddo.kiddomanager.exceptions.ParentsNotFoundException;
import kiddo.kiddomanager.models.entities.ParentsEntity;
import kiddo.kiddomanager.repositories.ParentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ParentsRepository parentsRepository;

    public void validateSubscription(String email) {
        ParentsEntity parentsEntityByEmail = parentsRepository.findParentsEntityByEmail(email);
        if (parentsEntityByEmail == null) {
            throw new ParentsNotFoundException(email);
        }
        parentsEntityByEmail.setAccountActive(true);
        parentsRepository.save(parentsEntityByEmail);
    }
}
