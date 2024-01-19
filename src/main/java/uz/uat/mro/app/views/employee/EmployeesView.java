package uz.uat.mro.app.views.employee;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.staff.Employee;
import uz.uat.mro.app.model.documents.staff.services.StaffService;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;

@PageTitle(value = "Сотрудники")
@Route(value = "common/employees", layout = EmployeeLayout.class)
public class EmployeesView extends VerticalLayout {
    private StaffService service;
    private OrganizationUnit organization;
    private GridCrud<Employee> crud;

    public EmployeesView(StaffService service) {
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        grid();
        add(crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(Employee.class);
        Grid<Employee> grid = crud.getGrid();

        grid.setColumns("surname", "name", "patronymic", "tabel");

        grid.getColumnByKey("surname").setHeader("Фамилия");
        grid.getColumnByKey("name").setHeader("Имя");
        grid.getColumnByKey("patronymic").setHeader("Отчество");
        grid.getColumnByKey("tabel").setHeader("Таб. Номер");

        crud.setAddOperation(service::saveEmployee);
        crud.setUpdateOperation(service::saveEmployee);
        crud.setDeleteOperation(service::deleteEmployee);
        crud.setFindAllOperation(() -> service.findAllEmployees(organization));
    }

}
