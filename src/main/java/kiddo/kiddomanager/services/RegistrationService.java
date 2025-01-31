package kiddo.kiddomanager.services;

import kiddo.kiddomanager.models.Parents;
import kiddo.kiddomanager.models.Personal;
import kiddo.kiddomanager.repositories.ParentsRepository;
import kiddo.kiddomanager.repositories.PersonalRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final ParentsRepository parentsRepository;
    private final PersonalRepository personalRepository;

    public RegistrationService(ParentsRepository parentsRepository, PersonalRepository personalRepository) {
        this.parentsRepository = parentsRepository;
        this.personalRepository = personalRepository;
    }

    public void registerParents(Parents parents) {
        
    }

    public void registerEmployee(Personal personal) {
    }
}
