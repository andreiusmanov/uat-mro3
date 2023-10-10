package uz.uat.mro.app.model.terms.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.common.Country;


public interface CountriesRepo extends ArangoRepository<Country, String> {
    public Country findByShortName(String shortName);
}
