package uz.uat.mro.app.model.terms.aircraft;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.documents.organization.Organization;

@Data
@Document("aircrafts")
public class Aircraft {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String regNumber;
    @Ref
    private Organization airline;
    @Ref
    private Organization owner;
    @Ref
    private AircraftModel model;

    

}
