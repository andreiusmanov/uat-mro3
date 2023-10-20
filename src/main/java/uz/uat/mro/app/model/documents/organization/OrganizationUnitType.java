package uz.uat.mro.app.model.documents.organization;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;

import lombok.Data;

@Data
@Document(collection = "organization_unit_types", allowUserKeys = true)
public class OrganizationUnitType {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
    private String description;
    
}
