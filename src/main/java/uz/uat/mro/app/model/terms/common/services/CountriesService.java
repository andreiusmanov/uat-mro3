package uz.uat.mro.app.model.terms.common.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.model.terms.common.repositories.CountriesRepo;

@Service
@AllArgsConstructor
public class CountriesService {
    private CountriesRepo countriesRepo;

    public List<Country> findAll() {
        return StreamSupport.stream(countriesRepo.findAll().spliterator(), false).toList();
    }

    public Country save(Country entity) {
        return countriesRepo.save(entity);
    }

    public void delete(Country entity) {
        countriesRepo.delete(entity);
    }

}
