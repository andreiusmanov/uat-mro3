package uz.uat.mro.app.model.documents.organization;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.organization.repositories.OrganizationStructureRepo;

@Service
@AllArgsConstructor
public class StructureService {
    private OrganizationStructureRepo repo;

    
}
