package uz.uat.mro.app.model.terms.library.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.Ref;

import lombok.Data;
import uz.uat.mro.app.model.terms.aircraft.AircraftSubzone;
import uz.uat.mro.app.model.terms.library.MpdEdition;
import uz.uat.mro.app.model.terms.library.MpdItem;

@Data
@Edge("mpd_items_subzones")
public class MpdItemsSubzones {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private MpdItem _from;
    private AircraftSubzone _to;
    @Ref(lazy = true)
    private MpdEdition edition;

}
