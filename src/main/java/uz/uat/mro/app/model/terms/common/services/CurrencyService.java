package uz.uat.mro.app.model.terms.common.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.terms.common.Currency;
import uz.uat.mro.app.model.terms.common.repositories.CurrencyRepo;

@Service
@AllArgsConstructor
public class CurrencyService {
    private CurrencyRepo currencyRepo;

    
    public List<Currency> findAll(){
        return StreamSupport.stream(currencyRepo.findAll().spliterator(), false).toList();
    }

    public Currency save(Currency entity){
        return currencyRepo.save(entity);
    }

    public void delete(Currency entity){
        currencyRepo.delete(entity);
    }




}
