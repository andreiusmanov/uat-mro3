package uz.uat.mro.app.model.documents.staff.edges;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.staff.Staff;

@Data
@Edge(collection = "has_staff")
public class HasStaff {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private OrganizationUnit organization;
    @To
    private Staff staff;
    private Number count;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
