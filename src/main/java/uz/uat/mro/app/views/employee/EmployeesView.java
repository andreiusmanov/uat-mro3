package uz.uat.mro.app.views.employee;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.staff.edges.HasEmployee;
import uz.uat.mro.app.model.documents.staff.services.StaffService;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;

@PageTitle(value = "Сотрудники")
@Route(value = "common/employees", layout = EmployeeLayout.class)
public class EmployeesView extends VerticalLayout {
    private StaffService service;
    private OrganizationUnit organization;
    private GridCrud<HasEmployee> crud;

    public EmployeesView(StaffService service) {
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        grid();
        add(crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(HasEmployee.class);
        Grid<HasEmployee> grid = crud.getGrid();

        grid.setColumns("employee.surname", "employee.name", "employee.patronymic", "employee.tabel");

        grid.getColumnByKey("employee.surname").setHeader("Фамилия");
        grid.getColumnByKey("employee.name").setHeader("Имя");
        grid.getColumnByKey("employee.patronymic").setHeader("Отчество");
        grid.getColumnByKey("employee.tabel").setHeader("Таб. Номер");
        grid.getColumnByKey("dateStart").setHeader("Дата приема");
        grid.getColumnByKey("dateEnd").setHeader("Дата увольнения");

        // crud.setAddOperation(service::saveHasEmployee);
        // crud.setUpdateOperation(service::saveHasEmployee);
        // crud.setDeleteOperation(service::deleteHasEmployee);
        // crud.setFindAllOperation(() -> service.findAllHasEmployee(organization));

    }

}
