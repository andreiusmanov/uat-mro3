package uz.uat.mro.app.model.terms.common.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import uz.uat.mro.app.model.terms.common.Employee;
import uz.uat.mro.app.model.terms.common.Person;
import uz.uat.mro.app.model.terms.common.edges.LinkPersonEmployee;
import uz.uat.mro.app.model.terms.common.repositories.EmployeesRepo;
import uz.uat.mro.app.model.terms.common.repositories.LinkPersonEmployeeRepo;
import uz.uat.mro.app.model.terms.common.repositories.PersonsRepo;

@Service
@AllArgsConstructor
public class StaffService {
    private PersonsRepo personsRepo;
    private EmployeesRepo employeesRepo;
    private LinkPersonEmployeeRepo linkPersonEmployeeRepo;

    public List<Person> findAllPersons() {
        return StreamSupport.stream(personsRepo.findAll().spliterator(), false).toList();
    }

    public Person savePerson(Person person) {
        return personsRepo.save(person);
    }

    public void deletePerson(Person person) {
        personsRepo.delete(person);
    }

    public List<Employee> findAllEmployees() {
        return StreamSupport.stream(employeesRepo.findAll().spliterator(), false).toList();
    }

    public Employee saveEmployee(Employee employee) {
        return employeesRepo.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeesRepo.delete(employee);
    }

    public void addPersonEmployee(Person from, Employee to, LocalDate dateStart, boolean status) {
        LinkPersonEmployee link = new LinkPersonEmployee(from, to, dateStart, true);
        linkPersonEmployeeRepo.save(link);
    }

    public void removePersonEmployee(Person from, Employee to) {
//        linkPersonEmployeeRepo.save(link);
    }

}
