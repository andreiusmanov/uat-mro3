package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
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
    private Grid<OrganizationUnit> grid;
    private MenuBar menu;

    public OrganizationStructureView(StructureService service) {
        this.service = service;
        this.structure = (OrganizationStructure) MyUtils.getAttribute(Keys.STRUCTURE);
        tabs();
        menu();
        add(menu, tabSheet);

    }

    private void menu() {
        menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        MenuItem home = menu.addItem("Организация");
        home.addClickListener(click -> {
            UI.getCurrent().navigate(StructuresView.class);
        });

    }

    private void tabs() {
        this.tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        Tab listTab = tabSheet.add("Список", listData());
        // listTab.add(listData());
        Tab chartTab = tabSheet.add("График", chartData());// , chartData());
    }

    private VerticalLayout listData() {
        H3 lo = new H3("Орг структура в виде списка");
        VerticalLayout v = new VerticalLayout();
        Grid<OrganizationUnit> grid = new Grid<>(OrganizationUnit.class);
        grid.setColumns("type", "shortName", "code", "name", "description");
        grid.getColumnByKey("type").setHeader("Тип");
        grid.getColumnByKey("shortName").setHeader("Аббрев.");
        grid.getColumnByKey("code").setHeader("Код");
        grid.getColumnByKey("name").setHeader("Наименование");
        grid.getColumnByKey("description").setHeader("Описание");

        v.add(lo, grid);
        return v;
    }

    private VerticalLayout chartData() {
        H3 lo = new H3("Орг структура в виде графика");
        VerticalLayout v = new VerticalLayout();

        Div picture = new Div(new Image("https://randomuser.me/api/portraits/men/76.jpg", ""));
        // Grid<OrganizationUnit> grid = new Grid<>(OrganizationUnit.class);
        v.add(lo, picture);
        return v;
    }

}
