package uz.uat.mro.app.model.documents.organization;

import org.springframework.data.annotation.Id;
import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.terms.organization.OrganizationUnitName;

@Data
@Document("organization_units")
public class OrganizationUnit {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String code;
    private String description;
    private String shortName;
    private String type;
    @Ref(lazy = false)
    private OrganizationUnitName unitName;
}
