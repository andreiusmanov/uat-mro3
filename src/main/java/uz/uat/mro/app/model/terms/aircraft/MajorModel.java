package uz.uat.mro.app.model.terms.aircraft;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.terms.organization.Organization;

@Data
@Document("major_models")

public class MajorModel {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String code;
    private String name;
    private String description;
    @Ref
    private Organization producer;

}
