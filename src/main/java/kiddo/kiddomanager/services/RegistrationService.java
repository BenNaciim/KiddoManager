package kiddo.kiddomanager.services;

import kiddo.kiddomanager.models.Parents;
import kiddo.kiddomanager.models.Personal;
import kiddo.kiddomanager.models.entities.ParentsEntity;
import kiddo.kiddomanager.models.entities.PersonalEntity;
import kiddo.kiddomanager.models.mappers.ParentsMapper;
import kiddo.kiddomanager.models.mappers.PersonalMapper;
import kiddo.kiddomanager.repositories.ParentsRepository;
import kiddo.kiddomanager.repositories.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final ParentsRepository parentsRepository;
    private final PersonalRepository personalRepository;
    private final PersonalMapper personalMapper;
    private final ParentsMapper parentsMapper;
    private final PasswordEncoder passwordEncoder;

    public ParentsEntity registerParents(Parents parents) {
        parents.setPassword(passwordEncoder.encode(parents.getPassword()));
        return parentsRepository.save(parentsMapper.mapParentsEntity(parents));
    }

    public PersonalEntity registerEmployee(Personal personal) {
        personal.setPassword(passwordEncoder.encode(personal.getPassword()));
        return personalRepository.save(personalMapper.mapPersonalEntity(personal));
    }

    public void removeParentsAccount(String email) {
        ParentsEntity parentsEntity = parentsRepository.findByEmail(email);
        parentsRepository.delete(parentsEntity);
    }

    public void removePersonalAccount(String email) {
        PersonalEntity personalEntity = personalRepository.findPersonalEntityByEmail(email);
        personalRepository.delete(personalEntity);
    }
}
