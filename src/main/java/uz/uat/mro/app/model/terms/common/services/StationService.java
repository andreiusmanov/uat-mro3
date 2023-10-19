package uz.uat.mro.app.model.terms.common.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.model.terms.common.Station;
import uz.uat.mro.app.model.terms.common.repositories.CountriesRepo;
import uz.uat.mro.app.model.terms.common.repositories.StationsRepo;

@Service
@AllArgsConstructor
public class StationService {
    private StationsRepo stationsRepo;
    private CountriesRepo countriesRepo;

    public Country findByCountry(Country country) {
        return stationsRepo.findByCountry(country);
    }

    public List<Country> findAllCountries(){
        return StreamSupport.stream(countriesRepo.findAll().spliterator(), false).toList();
    }

    public List<Station> findAllStations(){
        return StreamSupport.stream(stationsRepo.findAll().spliterator(), false).toList();
    }

    public Station save(Station station){
        return stationsRepo.save(station);
    }

    public void delete(Station station){
        stationsRepo.delete(station);
    }

}
