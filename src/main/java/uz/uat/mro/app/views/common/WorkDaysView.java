package uz.uat.mro.app.views.common;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Календарь")
@Route(value = "common/workdays", layout = CommonLayout.class)
public class WorkDaysView extends VerticalLayout {

}
