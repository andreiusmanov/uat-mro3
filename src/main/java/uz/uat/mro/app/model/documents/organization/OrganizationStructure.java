package uz.uat.mro.app.model.documents.organization;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document(value = "organization_structures")
public class OrganizationStructure {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private OrganizationUnit organization;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    public void deactivate() {
        this.active = false;
    }

}
