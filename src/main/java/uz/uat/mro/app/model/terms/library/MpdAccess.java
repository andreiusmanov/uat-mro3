package uz.uat.mro.app.model.terms.library;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.terms.aircraft.AircraftSubzone;
import uz.uat.mro.app.model.terms.aircraft.MajorModel;

@Data
@Document("mpd_accesses")
public class MpdAccess {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    @Ref(lazy = false)
    private AircraftSubzone subzone;
    @Ref(lazy = false)
    private MajorModel model;
    private String number;
    private BigDecimal open;
    private BigDecimal close;
    private String aplEngine;
    private String name;
    private Boolean synthetic;
    private String mmReference;
    private String subzoneString;

}
