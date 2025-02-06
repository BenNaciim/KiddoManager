package kiddo.kiddomanager.services;

import kiddo.kiddomanager.models.Child;
import kiddo.kiddomanager.models.entities.ChildEntity;
import kiddo.kiddomanager.models.mappers.ChildMapper;
import kiddo.kiddomanager.repositories.ChildRepository;
import kiddo.kiddomanager.repositories.ParentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChildService {
    private final ChildRepository childRepository;
    private final ParentsRepository parentsRepository;
    private final ChildMapper childMapper;

    public Child retrieveChildDetails(String firstName, String lastName) {
        ChildEntity childEntity = childRepository.findChildEntityByFirstNameAndLastName(firstName, lastName);
        return childMapper.mapChild(childEntity);
    }


    public void removeChild(Child child) {
        ChildEntity childEntity =  childMapper.mapChildEntity(child);
        childRepository.delete(childEntity);
    }

    public void addChild(Child child) {
        childRepository.save(childMapper.mapChildEntity(child));

    }
}
