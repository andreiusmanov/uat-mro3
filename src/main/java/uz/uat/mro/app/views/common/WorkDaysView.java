package uz.uat.mro.app.views.common;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.views.MainLayout;

@PageTitle(value = "Календарь")
@Route(value = "common/workdays", layout = MainLayout.class)
public class WorkDaysView extends VerticalLayout {

}
