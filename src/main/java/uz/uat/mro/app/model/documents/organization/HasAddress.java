package uz.uat.mro.app.model.documents.organization;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;

@Data
@Edge(collection = "has_address")
public class HasAddress {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From(lazy = true)
    private OrganizationUnit organization;
    @To(lazy = true)
    private Address address;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private boolean active;
   
}
