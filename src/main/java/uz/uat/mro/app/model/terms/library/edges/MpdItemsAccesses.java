package uz.uat.mro.app.model.terms.library.edges;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Edge;

import lombok.Data;
import uz.uat.mro.app.model.terms.aircraft.AircraftAccess;
import uz.uat.mro.app.model.terms.library.MpdItem;

@Data
@Edge("mpd_items_accesses")
public class MpdItemsAccesses {
    @Id
    private String id;
    @ArangoId
    private String arangoId;
    private MpdItem _from;
    private AircraftAccess _to;
}
