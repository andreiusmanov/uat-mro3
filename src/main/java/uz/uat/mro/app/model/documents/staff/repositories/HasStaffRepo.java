package uz.uat.mro.app.model.documents.staff.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import uz.uat.mro.app.model.documents.staff.edges.HasStaff;

public interface HasStaffRepo extends ArangoRepository<HasStaff, String> {

}
