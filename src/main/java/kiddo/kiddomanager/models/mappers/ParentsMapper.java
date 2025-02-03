package kiddo.kiddomanager.models.mappers;

import kiddo.kiddomanager.models.Parents;
import kiddo.kiddomanager.models.entities.ParentsEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ParentsMapper {
    ParentsEntity mapParentsEntity(Parents parents);
    Parents mapParents(ParentsEntity parentsEntity);

    @AfterMapping
    default void linkChildrenToParents(@MappingTarget ParentsEntity parentsEntity) {
        Optional.ofNullable(parentsEntity.getChildren())
                .ifPresent(children ->
                        children.forEach(child -> child.setParents(parentsEntity)));
    }
    @AfterMapping
    default void linkBillsToParents(@MappingTarget ParentsEntity parentsEntity) {
        Optional.ofNullable(parentsEntity.getBills())
                .ifPresent(bills ->
                        bills.forEach(bill -> bill.setParents(parentsEntity)));
    }

}
