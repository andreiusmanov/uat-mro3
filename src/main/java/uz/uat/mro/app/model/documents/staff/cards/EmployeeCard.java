package uz.uat.mro.app.model.documents.staff.cards;

import lombok.Data;
import uz.uat.mro.app.model.documents.staff.Employee;
import uz.uat.mro.app.model.documents.staff.edges.HasEmployee;

@Data
public class EmployeeCard {
private Employee employee;
private HasEmployee hasEmployee;
}
