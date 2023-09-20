package uz.uat.mro.app.model.terms.organization.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.app.model.terms.organization.OrganizationUnit;

@Data
@Edge("has_unit_unit")
public class HasUnitUnit {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private OrganizationUnit organizationUnitFrom;
    @To
    private OrganizationUnit organizationUnitTo;
}
