package uz.uat.mro.app.model.terms.library;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.terms.aircraft.MajorModel;

@Data
@Document("mpd_zones")

public class MpdZone {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String code;
    private String name;
    @Ref
    private MajorModel model;
  
}
