package uz.uat.mro.app.model.documents.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.organization.edges.HasUnit;

public interface HasOrganizationUnitRepo extends ArangoRepository<HasUnit, String> {

}
