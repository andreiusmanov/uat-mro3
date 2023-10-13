package uz.uat.mro.app.model.documents.organization.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;

@Data
@Edge("has_unit")
public class HasUnit {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private OrganizationUnit principle;
    @To
    private OrganizationUnit subordinate;
}
