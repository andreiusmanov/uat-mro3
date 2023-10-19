package uz.uat.mro.app.views.common;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Страны")
@Route(value = "common/countries", layout = CommonLayout.class)
public class CountriesView extends VerticalLayout {

}
