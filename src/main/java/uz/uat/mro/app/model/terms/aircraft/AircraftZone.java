package uz.uat.mro.app.model.terms.aircraft;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;

@Data
@Document("aircraft_zones")

public class AircraftZone {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String code;
    private String name;
    @Ref
    private MajorModel model;
  
}
