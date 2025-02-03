package kiddo.kiddomanager.models.mappers;

import kiddo.kiddomanager.models.Bills;
import kiddo.kiddomanager.models.entities.BillsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillsMapper {

    BillsEntity mapBillsEntity(Bills bills);
    Bills mapBills(BillsEntity billsEntity);

}