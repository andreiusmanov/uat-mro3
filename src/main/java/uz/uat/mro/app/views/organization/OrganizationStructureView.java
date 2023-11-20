package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationStructure;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.StructureService;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;

@PageTitle(value = "Орг. Структура")
@Route(value = "org-structure")
public class OrganizationStructureView extends VerticalLayout {
    private StructureService service;
    private OrganizationStructure structure;
    private TabSheet tabSheet;
    private Tab listTab;
    private Tab chartTab;
    private Grid<OrganizationUnit> grid;

    public OrganizationStructureView(StructureService service) {
        this.service = service;
        this.structure = (OrganizationStructure) MyUtils.getAttribute(Keys.STRUCTURE);
        tabs();
        add(tabSheet);

    }

    private void tabs() {
        this.tabSheet = new TabSheet();
        listTab = tabSheet.add("Список", listData());
        listTab.add(listData());
        chartTab = tabSheet.add("График", chartData());
    }

    private VerticalLayout listData() {
        H3 lo = new H3("Орг структура в виде списка");
        VerticalLayout v = new VerticalLayout();
        Grid<OrganizationUnit> grid = new Grid<>(OrganizationUnit.class);
        v.add(lo, grid);
        return v;
    }

    private VerticalLayout chartData() {
        H3 lo = new H3("Орг структура в виде графика");
        VerticalLayout v = new VerticalLayout();
        Grid<OrganizationUnit> grid = new Grid<>(OrganizationUnit.class);
        v.add(lo, grid);
        return v;
    }

}
