package uz.uat.mro.app.model.terms.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.common.UomType;

public interface UomTypeRepo extends ArangoRepository<UomType, String>{
    
}
