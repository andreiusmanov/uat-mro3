package uz.uat.mro.app.model.documents.organization;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
import uz.uat.mro.app.model.documents.organization.repositories.HasOrganizationUnitRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationStructureRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitTypeRepo;

@Service
@AllArgsConstructor
public class StructureService {
    private OrganizationStructureRepo repo;
    private OrganizationUnitTypeRepo typeRepo;
    private OrganizationUnitRepo unitRepo;
    private HasOrganizationUnitRepo hasUnitRepo;

    public List<OrganizationUnitType> findAllTypes() {
        return StreamSupport.stream(typeRepo.findAll().spliterator(), false).toList();
    }

    public OrganizationUnit saveUnit(OrganizationUnit unit) {
        return unitRepo.save(unit);
    }

    public HasOrganizationUnit saveHasUnit(HasOrganizationUnit hasUnit) {
        return hasUnitRepo.save(hasUnit);
    }

    public List<HasOrganizationUnit> findOrganizationUnits(OrganizationUnit unit) {
        return hasUnitRepo.findOrganizationUnits(unit.getArangoId());
    }

    public OrganizationStructure saveStructure(OrganizationStructure entity) {
        return repo.save(entity);
    }

    public void deleteStructure(OrganizationStructure entity) {
        repo.delete(entity);
    }

    public void saveNewUnitAndEdge(OrganizationUnit master, OrganizationUnit subordinate, HasOrganizationUnit edge) {
        try {
            saveUnit(subordinate);
            edge.setMaster(master);
            edge.setSubordinate(subordinate);
            saveHasUnit(edge);
        } catch (Exception e) {

        }

    }

}
