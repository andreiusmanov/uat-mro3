package uz.uat.mro.app.model.terms.library.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.library.AtaChapter;

public interface AtaChaptersRepo extends ArangoRepository<AtaChapter, String>{
    
}
