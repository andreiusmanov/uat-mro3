package uz.uat.mro.app.model.terms.organization.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.organization.Organization;

public interface OrganizationRepo extends ArangoRepository<Organization, String> {

}
