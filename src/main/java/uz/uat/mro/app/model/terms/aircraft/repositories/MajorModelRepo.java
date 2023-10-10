package uz.uat.mro.app.model.terms.aircraft.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.aircraft.MajorModel;


public interface MajorModelRepo extends ArangoRepository<MajorModel, String> {

}
