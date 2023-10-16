package uz.uat.mro.app.model.documents.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.organization.edges.HasAddress;

public class HasAddressRepo extends ArangoRepository<HasAddress, String> {
    
}
