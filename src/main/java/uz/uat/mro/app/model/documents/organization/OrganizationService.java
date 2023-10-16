package uz.uat.mro.app.model.documents.organization;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
// import uz.uat.mro.app.model.documents.organization.repositories.AddressRepo;
// import uz.uat.mro.app.model.documents.organization.repositories.HasAddressRepo;
import uz.uat.mro.app.model.documents.organization.repositories.HasOrganizationUnitRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitRepo;

@Service
@AllArgsConstructor
public class OrganizationService {
    private OrganizationUnitRepo unitRepo;
    private HasOrganizationUnitRepo hasUnitRepo;
    // private AddressRepo addressRepo;
    // private HasAddressRepo hasAddressRepo;

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

    // public void linkAddress(OrganizationUnit master, Address address) {
    // Address savedAddress = addressRepo.save(address);
    // HasAddress hasAddress = new HasAddress();
    // hasAddress.setOrganization(master);
    // hasAddress.setAddress(savedAddress);
    // hasAddressRepo.save(hasAddress);
    // }

}
