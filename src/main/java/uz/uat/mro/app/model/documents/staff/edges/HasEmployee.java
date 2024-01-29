package uz.uat.mro.app.model.documents.staff.edges;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.staff.Employee;

@Data
@Edge(collection = "has_employee")
public class HasEmployee {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private OrganizationUnit organization;
    @To
    private Employee employee;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private boolean active;

}
