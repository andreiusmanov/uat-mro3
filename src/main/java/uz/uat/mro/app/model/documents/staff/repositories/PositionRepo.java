package uz.uat.mro.app.model.documents.staff.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.staff.Staff;

public interface PositionRepo extends ArangoRepository<Staff,String>{
    
}
