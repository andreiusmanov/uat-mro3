package uz.uat.mro.app.model.terms.common.services;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.terms.common.Employee;
import uz.uat.mro.app.model.terms.common.repositories.EmployeesRepo;

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
