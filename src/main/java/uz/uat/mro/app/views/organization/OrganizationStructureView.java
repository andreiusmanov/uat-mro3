package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.StructureService;

@PageTitle(value = "Орг. Структура")
@Route(value = "org-structure")
public class OrganizationStructureView extends VerticalLayout {
    private StructureService service;
    private TabSheet tabSheet;
    private Tab listTab;
    private Tab chartTab;

    public OrganizationStructureView(StructureService service) {
        this.service = service;
        tabs();
        add(tabSheet);

    }

    private void tabs() {
        this.tabSheet = new TabSheet();
        this.listTab = new Tab("Список");
        listTab.add(new H3("Орг структура в виде списка"));
        this.chartTab = new Tab("График");
        chartTab.add(new H3("Орг структура в виде графика"));
    }

}
