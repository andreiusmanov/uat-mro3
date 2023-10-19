package uz.uat.mro.app.views.common;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle(value = "Общие данные")
@Route(value = "common/start", layout = CommonLayout.class)
public class CommonsView extends VerticalLayout {

}
