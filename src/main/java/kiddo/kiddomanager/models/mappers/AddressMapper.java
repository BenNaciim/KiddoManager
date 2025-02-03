package kiddo.kiddomanager.models.mappers;

import kiddo.kiddomanager.models.Address;
import kiddo.kiddomanager.models.entities.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity mapAddressEntity(Address address);
    Address mapAddress(AddressEntity addressEntity);

}
