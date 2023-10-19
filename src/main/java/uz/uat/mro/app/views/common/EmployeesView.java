package uz.uat.mro.app.views.common;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Сотрудники")
@Route(value = "common/employees", layout = CommonLayout.class)
public class EmployeesView extends VerticalLayout {

}
