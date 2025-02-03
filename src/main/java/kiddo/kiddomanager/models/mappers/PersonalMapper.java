package kiddo.kiddomanager.models.mappers;

import kiddo.kiddomanager.models.Personal;
import kiddo.kiddomanager.models.entities.PersonalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonalMapper {

    Personal mapPersonal(PersonalEntity personalEntity);
    PersonalEntity mapPersonalEntity(Personal personal);
}
