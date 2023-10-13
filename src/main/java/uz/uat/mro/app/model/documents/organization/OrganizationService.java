package uz.uat.mro.app.model.documents.organization;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitRepo;

@Service
@AllArgsConstructor
public class OrganizationService {
    private OrganizationUnitRepo unitRepo;

    public OrganizationUnit save(OrganizationUnit unit){
        return unitRepo.save(unit);
    }

    public void delete(OrganizationUnit unit){
         unitRepo.delete(unit);
    }

    public List<OrganizationUnit> findSubordinates(OrganizationUnit principle){
        return unitRepo.getOrganizationUnitsByOrganization(principle.getArangoId());
    }



}
