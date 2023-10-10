package uz.uat.mro.app.model.terms.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.common.Uom;

public interface UomRepo extends ArangoRepository<Uom, String> {

}