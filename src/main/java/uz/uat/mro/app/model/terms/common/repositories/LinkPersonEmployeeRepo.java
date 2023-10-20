package uz.uat.mro.app.model.terms.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.common.edges.LinkPersonEmployee;

public interface LinkPersonEmployeeRepo extends ArangoRepository<LinkPersonEmployee, String>{
    
}
