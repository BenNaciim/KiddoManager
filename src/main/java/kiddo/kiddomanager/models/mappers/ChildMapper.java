package kiddo.kiddomanager.models.mappers;

import kiddo.kiddomanager.models.Child;
import kiddo.kiddomanager.models.entities.ChildEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ChildMapper {
    ChildEntity mapChildEntity(Child child);
    Child mapChild(ChildEntity childEntity);

    @AfterMapping
    default void linkActivities(@MappingTarget ChildEntity childEntity) {
        Optional.ofNullable(childEntity.getTasks())
                .ifPresent(tasks ->
                        tasks.forEach(task -> task.setChild(childEntity)));
    }
}
