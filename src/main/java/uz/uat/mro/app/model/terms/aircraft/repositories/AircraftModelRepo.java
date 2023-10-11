package uz.uat.mro.app.model.terms.aircraft.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.aircraft.AircraftModel;


public interface AircraftModelRepo extends ArangoRepository<AircraftModel, String>{
    
}
