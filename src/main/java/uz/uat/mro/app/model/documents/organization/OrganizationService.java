package uz.uat.mro.app.model.documents.organization;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
import uz.uat.mro.app.model.documents.organization.repositories.HasOrganizationUnitRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationStructureRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitRepo;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationUnitTypeRepo;
import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.model.terms.common.repositories.CountriesRepo;

@Service
@AllArgsConstructor
public class OrganizationService {
    private CountriesRepo countriesRepo;
    private OrganizationUnitRepo unitRepo;
    private OrganizationUnitTypeRepo unitTypeRepo;
    private HasOrganizationUnitRepo hasUnitRepo;
    private OrganizationStructureRepo structureRepo;

    public List<Country> findAllCountries() {
        return StreamSupport.stream(countriesRepo.findAll().spliterator(), false).toList();
    }

    public OrganizationUnit getOrganizationUnitById(String unitId) {
        return unitRepo.findById(unitId).get();
    }

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

    public Optional<OrganizationUnitType> findOrganizationType(String typeCode) {
        OrganizationUnitType type = new OrganizationUnitType();
        type.setCode(typeCode);
        return unitTypeRepo.findOne(Example.of(type));
    }

    public List<OrganizationUnitType> findAllOrganizationTypes() {
        return StreamSupport.stream(unitTypeRepo.findAll().spliterator(), false).toList();
    }

    /**
     * List all links between organization and its's units 
     */

     public List<HasOrganizationUnit> findOrganizationUnits(OrganizationUnit unit){
        return hasUnitRepo.findOrganizationUnits(unit.getArangoId());
     }

     public HasOrganizationUnit saveHasUnit(HasOrganizationUnit hasUnit){
        return hasUnitRepo.save(hasUnit);
     }

     public void deleteHasUnit(HasOrganizationUnit hasUnit){
        hasUnitRepo.delete(hasUnit);
     }

     public HasOrganizationUnit findHasOrganizationUnitById(HasOrganizationUnit hasUnit){
        return hasUnitRepo.findById(hasUnit.getArangoId()).get();
     }



     // Organization structures

     public OrganizationStructure saveStructure(OrganizationStructure structure){
        return structureRepo.save(structure);
     }
     public void deleteStructure(OrganizationStructure structure){
        structureRepo.delete(structure);
     }
     
public List<OrganizationStructure> findAllStructures(OrganizationUnit organization){
    return structureRepo.getStructuresByOrganization(organization.getArangoId());
}

}
