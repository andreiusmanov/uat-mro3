package uz.uat.mro.app.model.terms.organization.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.app.model.terms.organization.Organization;
import uz.uat.mro.app.model.terms.organization.OrganizationUnit;

@Data
@Edge("has_unit")
public class HasUnit {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private Organization organization;
    @To
    private OrganizationUnit organizationUnit;

}
