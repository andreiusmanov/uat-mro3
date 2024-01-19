package uz.uat.mro.app.model.documents.staff.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.staff.Employee;

public interface EmployeesRepo extends ArangoRepository<Employee, String>{
    
 @Query("")   
public List<Employee> findByOrganization(@Param("unit") String unit);


}
