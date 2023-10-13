package uz.uat.mro.app.model.documents.organization;

import java.util.Collection;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;
import com.arangodb.springframework.annotation.Relations;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.edges.HasFacility;
import uz.uat.mro.app.model.documents.organization.edges.HasUnit;
import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.model.terms.organization.OrganizationUnitName;

@Data
@Document("organization_units")
public class OrganizationUnit {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = true)
    private Country country;
    private Address address;
    private String name;
    private String code;
    private String description;
    private String shortName; 
    private String type;
    @Ref(lazy = false)
    private OrganizationUnitName unitName;
    @Relations(edges = HasUnit.class, lazy = false)
    private Collection<OrganizationUnit> units;
    @Relations(edges = HasFacility.class, lazy = false)
    private Collection<Facility> facilities;
}
