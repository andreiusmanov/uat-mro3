package uz.uat.mro.app.model.documents.organization;

import java.util.Collection;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.edges.HasFacility;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
import uz.uat.mro.app.model.terms.common.Country;

@Data
@Document("organization_units")
public class OrganizationUnit {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private Country country;
    private String name;
    private String code;
    private String description;
    private String shortName;
    @Ref(lazy = false)
    private OrganizationUnitType type;
    @Relations(edges = HasOrganizationUnit.class, lazy = false)
    private Collection<OrganizationUnit> units;
    @Relations(edges = HasFacility.class, lazy = false)
    private Collection<Facility> facilities;
}
