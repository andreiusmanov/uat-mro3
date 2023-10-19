package uz.uat.mro.app.views.common;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Валюты")
@Route(value = "common/currencies", layout = CommonLayout.class)
public class CurrenciesView extends VerticalLayout {

}
