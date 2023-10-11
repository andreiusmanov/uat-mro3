package uz.uat.mro.app.model.terms.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.organization.OrganizationUnitName;

public interface OrganizationUnitNameRepo extends ArangoRepository<OrganizationUnitName, String>{
    
}
