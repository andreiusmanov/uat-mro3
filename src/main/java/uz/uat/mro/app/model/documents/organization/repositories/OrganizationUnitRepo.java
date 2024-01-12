package uz.uat.mro.app.model.documents.organization.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;

public interface OrganizationUnitRepo extends ArangoRepository<OrganizationUnit, String> {

    @Query("for u in has_unit for i in organization_units filter u._from == @organization && i._id == u._to return i")
    public List<OrganizationUnit> getOrganizationUnitsByOrganization(@Param("organization") String organization);

    @Query(value = "for i in organization_units filter i.type == @typeName return i")
    public List<OrganizationUnit> findMainOrganizations(@Param("typeName") String typeName);

    @Query(value = "for i in organization_units filter i.type == @typeName return i")
    public List<OrganizationUnit> findUnitByType(@Param("typeName") String typeName);


}
