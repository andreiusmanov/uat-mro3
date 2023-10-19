package uz.uat.mro.app.views.common;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Персоналии")
@Route(value = "common/persons", layout = CommonLayout.class)
public class PersonsView extends VerticalLayout {

}
