package uz.uat.mro.app.model.documents.staff.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;

import uz.uat.mro.app.model.documents.staff.Employee;
import uz.uat.mro.app.model.documents.staff.Staff;

@Edge(collection = "has_position")
public class HasPosition {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @From
    private Employee employee;
    @To
    private Staff staff;

}
