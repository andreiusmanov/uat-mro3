package uz.uat.mro.app.model.documents.staff;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;

@Data
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private OrganizationUnit organization;
    private String name;
    private String surname;
    private String patronymic;
    private String tabel;
    @Ref(lazy =true)
    private List<Staff> staff;
}
