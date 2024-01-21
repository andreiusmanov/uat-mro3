package uz.uat.mro.app.model.documents.staff;

import lombok.Data;
import uz.uat.mro.app.model.documents.staff.edges.HasEmployee;

@Data
public class ComboEmployee {
    private Employee employee;
    private HasEmployee hasEmployee;
}
