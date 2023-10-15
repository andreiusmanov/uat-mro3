package uz.uat.mro.app.model.documents.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;

public interface HasOrganizationUnitRepo extends ArangoRepository<HasOrganizationUnit, String> {

}
