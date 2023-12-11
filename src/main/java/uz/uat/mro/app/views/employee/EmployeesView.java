package uz.uat.mro.app.views.employee;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
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
    private Grid<Employee> grid;
    private MenuBar menu;
    private MenuItem addItem;

    public EmployeesView(StaffService service) {
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        grid();
        menu();
        add(grid);
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        addItem = menu.addItem("Добавить");
        addItem.addClickListener(click -> {
            dialog();
        });
    }

    private void dialog() {
    Dialog d = new Dialog("Редактирование данных сотрудника");
    d.add(new EmployeeForm(organization));
    Button saveButton = new Button("Сохранить");
    // saveButton.addClickListener(null);
    
    }

    private void grid() {
        this.grid = new Grid<>(Employee.class);

    }

}
