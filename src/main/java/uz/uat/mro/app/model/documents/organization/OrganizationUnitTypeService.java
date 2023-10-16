package uz.uat.mro.app.model.documents.organization;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitTypeRepo;

@AllArgsConstructor
@Service
public class OrganizationUnitTypeService {
    private OrganizationUnitTypeRepo repo;

    public OrganizationUnitType save(OrganizationUnitType entity) {
        return repo.save(entity);
    }

    public void delete(OrganizationUnitType entity) {
        repo.delete(entity);
    }

    public List<OrganizationUnitType> queryAllUnitNames() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }

}
