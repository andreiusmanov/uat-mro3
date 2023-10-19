package uz.uat.mro.app.model.terms.common.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.terms.common.Maintenance;

public interface MaintenanceRepo extends ArangoRepository<Maintenance, String> {

}
