package uz.uat.mro.app.model.terms.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.model.terms.common.Station;


public interface StationsRepository extends ArangoRepository<Station, String> {

    Country findByCountry(Country country);
}
