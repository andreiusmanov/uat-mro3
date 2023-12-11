package uz.uat.mro.app.views.employee;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.staff.Employee;
import uz.uat.mro.app.model.documents.staff.services.StaffService;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;

@PageTitle(value = "Карта сотрудника")
@Route(value = "organization/employee")
public class EmployeeView extends VerticalLayout {

    private StaffService service;
    private EmployeeForm form;
    private Employee employee;
    private OrganizationUnit organization;

    public EmployeeView(StaffService service) {
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        form();
    }

    private void form() {
        this.form = new EmployeeForm(organization);
    }

}
