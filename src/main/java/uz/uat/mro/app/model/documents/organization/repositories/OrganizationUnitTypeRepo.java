package uz.uat.mro.app.model.documents.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;

public interface OrganizationUnitTypeRepo extends ArangoRepository<OrganizationUnitType, String>{
    
}
