package uz.uat.mro.app.model.documents.staff.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.documents.staff.Employee;
import uz.uat.mro.app.model.documents.staff.repositories.EmployeesRepo;

@Service
@AllArgsConstructor
public class StaffService {
    private EmployeesRepo employeesRepo;


    public List<Employee> findAllEmployees() {
        return StreamSupport.stream(employeesRepo.findAll().spliterator(), false).toList();
    }

    public Employee saveEmployee(Employee employee) {
        return employeesRepo.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeesRepo.delete(employee);
    }


}
