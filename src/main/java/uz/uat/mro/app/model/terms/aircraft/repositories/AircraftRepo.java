package uz.uat.mro.app.model.terms.aircraft.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.aircraft.Aircraft;

public interface AircraftRepo extends ArangoRepository<Aircraft, String>{
    
}
