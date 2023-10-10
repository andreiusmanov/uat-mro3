package uz.uat.mro.app.model.terms.common.repositories;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.common.Sector;


public interface SectorRepo extends ArangoRepository<Sector, String> {

    @Query(value = "FOR s IN sectors FILTER s.departmentId == @departmentId RETURN s")
    List<Sector> findByDepartmentId(String departmentId);

}
