package uz.uat.mro.app.model.documents.organization.edges;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.terms.organization.Address;

@Data
@Edge(value = "has_address")
public class HasAddress {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private OrganizationUnit organization;
    @To
    private Address address;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private boolean active;
   
}
