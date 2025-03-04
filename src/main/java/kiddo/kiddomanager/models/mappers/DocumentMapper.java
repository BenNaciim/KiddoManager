package kiddo.kiddomanager.models.mappers;

import kiddo.kiddomanager.models.Document;
import kiddo.kiddomanager.models.entities.DocumentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentEntity mapDocumentEntity(Document document);
    Document mapActivity(DocumentEntity documentEntity);

}
