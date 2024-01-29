package uz.uat.mro.app.model.documents.staff.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.staff.Employee;
import uz.uat.mro.app.model.documents.staff.cards.EmployeeCard;
import uz.uat.mro.app.model.documents.staff.edges.HasEmployee;
import uz.uat.mro.app.model.documents.staff.repositories.EmployeesRepo;
import uz.uat.mro.app.model.documents.staff.repositories.HasEmployeeRepo;

@Service
@AllArgsConstructor
public class StaffService {
    private EmployeesRepo employeesRepo;
    private HasEmployeeRepo hasEmployeeRepo;

// operations with HasEmployee class

    public List<Employee> findAllEmployees() {
        return StreamSupport.stream(employeesRepo.findAll().spliterator(), false).toList();
    }
    
    public List<Employee> findAllEmployees(OrganizationUnit organization) {
        return employeesRepo.findByOrganization(organization.getArangoId());
    }

    public Employee saveEmployeeCard(EmployeeCard card) {
        return employeesRepo.save(card.getEmployee());
    }

    public void deleteEmployee(Employee employee) {
        employeesRepo.delete(employee);
    }

    

}
