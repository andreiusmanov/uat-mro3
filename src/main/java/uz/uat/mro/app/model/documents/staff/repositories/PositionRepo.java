package uz.uat.mro.app.model.documents.staff.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.staff.Position;

public interface PositionRepo extends ArangoRepository<Position,String>{
    
}
