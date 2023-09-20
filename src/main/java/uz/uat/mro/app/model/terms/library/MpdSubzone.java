package uz.uat.mro.apps.model.alt.library;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.apps.model.alt.aircraft.MajorModel;

@Data
@Document("mpd_subzones")
public class MpdSubzone {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private String code;
    private String name;
    @Ref(lazy = false)
    private MpdZone zone;
    @Ref(lazy = false)
    private MajorModel model;
}
