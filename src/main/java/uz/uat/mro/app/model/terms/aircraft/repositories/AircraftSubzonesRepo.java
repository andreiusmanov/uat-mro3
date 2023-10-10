package uz.uat.mro.app.model.terms.aircraft.repositories;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.aircraft.AircraftSubzone;

public interface AircraftSubzonesRepo extends ArangoRepository<AircraftSubzone, String> {

    @Query("FOR subzone IN aircraft_subzones FILTER subzone.model == @model sort subzone.code RETURN subzone")
    List<AircraftSubzone> findByModel(String model);
}
