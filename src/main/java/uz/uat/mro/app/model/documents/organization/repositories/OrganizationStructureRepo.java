package uz.uat.mro.app.model.documents.organization.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.organization.OrganizationStructure;

public interface OrganizationStructureRepo extends ArangoRepository<OrganizationStructure, String> {

    @Query("for u in organization_structures filter u.organization == @organization return u")
    public List<OrganizationStructure> getStructuresByOrganization(@Param("organization") String organization);

}
