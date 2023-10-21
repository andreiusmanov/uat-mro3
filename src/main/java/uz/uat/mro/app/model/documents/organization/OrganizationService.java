package uz.uat.mro.app.model.documents.organization;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
import uz.uat.mro.app.model.documents.organization.repositories.HasOrganizationUnitRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitTypeRepo;

@Service
@AllArgsConstructor
public class OrganizationService {
    private OrganizationUnitRepo unitRepo;
    private OrganizationUnitTypeRepo unitTypeRepo;
    private HasOrganizationUnitRepo hasUnitRepo;

    public OrganizationUnit save(OrganizationUnit unit) {
        return unitRepo.save(unit);
    }

    public void delete(OrganizationUnit unit) {
        unitRepo.delete(unit);
    }

    public List<OrganizationUnit> findSubordinates(OrganizationUnit principle) {
        return unitRepo.getOrganizationUnitsByOrganization(principle.getArangoId());
    }

    public void linkOrganizationUnits(OrganizationUnit master, OrganizationUnit subordinate,
            HasOrganizationUnit link) {

        HasOrganizationUnit hasUnit = new HasOrganizationUnit();
        hasUnit.setMaster(master);
        hasUnit.setSubordinate(subordinate);
        hasUnitRepo.save(hasUnit);

    }

    public void linkOrganizationUnits(OrganizationUnit master, OrganizationUnit subordinate) {
        OrganizationUnit savedSubordinate = save(subordinate);
        HasOrganizationUnit hasUnit = new HasOrganizationUnit();
        hasUnit.setMaster(master);
        hasUnit.setSubordinate(savedSubordinate);
        hasUnitRepo.save(hasUnit);
    }

    public List<OrganizationUnit> findMainOrganizations(String type) {
        return unitRepo.findMainOrganizations(type);
    }

public Optional<OrganizationUnitType> findOrganizationType(){
    OrganizationUnitType type = new OrganizationUnitType();
    type.setCode("Организация");
    return unitTypeRepo.findOne(Example.of(type));
}

}
