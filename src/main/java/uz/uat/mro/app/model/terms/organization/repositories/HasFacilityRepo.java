package uz.uat.mro.app.model.terms.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.organization.edges.HasFacility;

public interface HasFacilityRepo extends ArangoRepository<HasFacility, String> {
    
}
