package uz.uat.mro.app.model.documents.staff;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.staff.edges.HasEmployee;
import uz.uat.mro.app.model.documents.staff.edges.HasStaff;

@Data
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String name;
    private String surname;
    private String patronymic;
    private String tabel;
    @Relations(edges = HasEmployee.class, lazy = true)
    private List<OrganizationUnit> organization;
    @Relations(edges = HasStaff.class, lazy = true)
    private List<Staff> staff;
}
