package uz.uat.mro.app.views.employee;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.views.common.CommonLayout;

@PageTitle(value = "Сотрудники")
@Route(value = "common/employees", layout = EmployeeLayout.class)
public class EmployeesView extends VerticalLayout {

}
