package uz.uat.mro.app.model.documents.organization.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;

public interface HasOrganizationUnitRepo extends ArangoRepository<HasOrganizationUnit, String> {

@Query(value = "FOR v, e IN 1..100 OUTBOUND @unit GRAPH OrganizationChartGraph return e")
public List<HasOrganizationUnit> findOrganizationUnits(@Param("unit") String unit);
}

//'organization_units/819313'