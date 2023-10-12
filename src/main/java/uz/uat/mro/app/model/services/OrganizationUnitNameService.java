package uz.uat.mro.app.model.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.terms.organization.OrganizationUnitName;
import uz.uat.mro.app.model.terms.organization.repositories.OrganizationUnitNameRepo;

@AllArgsConstructor
@Service
public class OrganizationUnitNameService {
    private OrganizationUnitNameRepo repo;

    public OrganizationUnitName save(OrganizationUnitName entity) {
        return repo.save(entity);
    }

    public void delete(OrganizationUnitName entity) {
        repo.delete(entity);
    }

    public List<OrganizationUnitName> queryAllUnitNames() {
        return StreamSupport.stream(repo.findAll().spliterator(), false).toList();
    }

}