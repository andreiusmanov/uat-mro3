package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationStructure;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.StructureService;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;
import uz.uat.mro.app.views.organization.forms.NewOrgUnitDialog;

@PageTitle(value = "Орг. Структура")
@Route(value = "org-structure")
public class OrganizationStructureView extends VerticalLayout {
    private StructureService service;
    private OrganizationStructure structure;
    private TabSheet tabSheet;
    private Grid<HasOrganizationUnit> grid;
    private OrganizationUnit organization;
    private MenuBar menu;
    private MenuItem findItem;
    private MenuItem addItem;
    private MenuItem editItem;
    private MenuItem deleteItem;
    private HasOrganizationUnit hasUnit;
    private GridListDataView<HasOrganizationUnit> dataView;
    private OrganizationUnit selectedUnit;
    private NewOrgUnitDialog newDialog;

    public OrganizationStructureView(StructureService service) {
        this.service = service;
        this.structure = (OrganizationStructure) MyUtils.getAttribute(Keys.STRUCTURE);
        this.organization = structure.getOrganization();
        tabs();
        add(tabSheet);
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        menu.addItem(VaadinIcon.HOME_O.create(), click -> {
            UI.getCurrent().navigate(OrganizationStructuresView.class);
        });

        findItem = menu.addItem("Показать", "Показать орг. структуру", click -> {
            editDialog();
            newDialog.open();
            Notification.show("Показать");
        });
        addItem = menu.addItem("Добавить", "Добавить орг. структуру", click -> {
            newDialog();
            newDialog.open();
            Notification.show("Клик кнопка Добавить");
        });

        editItem = menu.addItem("Редактировать", "Редактировать орг. структуру", click -> {
            editDialog();
            newDialog.open();
            Notification.show("Редактировать");
        });
        deleteItem = menu.addItem("Удалить", "Удалить орг. структуру", click -> {
            Notification.show("Удалить");
        });
    }

    private void grid() {
        this.grid = new Grid<>(HasOrganizationUnit.class);
        this.grid.setItems(service.findOrganizationUnits(organization));
        this.grid.setColumns("subordinate.name", "subordinate.code", "subordinate.shortName", "subordinate.description",
                "active");
        this.grid.getColumnByKey("subordinate.name").setHeader("Наименование");
        this.grid.getColumnByKey("subordinate.code").setHeader("Код");
        this.grid.getColumnByKey("subordinate.shortName").setHeader("Аббрев.");
        this.grid.getColumnByKey("subordinate.description").setHeader("Описание");
        this.grid.getColumnByKey("active").setHeader("Статус");

        // filter
        dataView = grid.getListDataView();

        this.findItem.setEnabled(false);
        this.editItem.setEnabled(false);
        this.deleteItem.setEnabled(false);

        // listener
        grid.getSelectionModel().addSelectionListener(selected -> {
            boolean res = grid.getSelectionModel().getFirstSelectedItem().isPresent();
            if (res) {
                this.hasUnit = grid.getSelectionModel().getFirstSelectedItem().get();
                this.selectedUnit = hasUnit.getMaster();
                this.findItem.setEnabled(res);
                this.editItem.setEnabled(res);
                this.deleteItem.setEnabled(res);
            } else {
                this.selectedUnit = organization;
                this.findItem.setEnabled(!res);
                this.editItem.setEnabled(!res);
                this.deleteItem.setEnabled(!res);
            }
        });
    }

    private void tabs() {
        this.tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        tabSheet.add("Список", listData());
        tabSheet.add("Схема", chartData());// , chartData());
    }

    private VerticalLayout listData() {

        H3 lo = new H3("Орг структура в виде списка");
        VerticalLayout v = new VerticalLayout();
        menu();
        grid();

        v.add(lo, menu, grid);
        return v;
    }

    private VerticalLayout chartData() {
        H3 lo = new H3("Орг структура в виде схемы");
        VerticalLayout v = new VerticalLayout();

        Div picture = new Div(new Image("https://randomuser.me/api/portraits/men/76.jpg", ""));
        v.add(lo, picture);
        return v;
    }

    private void editDialog() {
        this.newDialog = new NewOrgUnitDialog(service, selectedUnit, false);
        newDialog.addDialogCloseActionListener(event -> {
            grid.getDataProvider().refreshAll();
        });
        this.newDialog.open();
    }

    private void newDialog() {
        this.newDialog = new NewOrgUnitDialog(service, selectedUnit, false);
        newDialog.addDialogCloseActionListener(event -> {
            grid.getDataProvider().refreshAll();
        });
        this.newDialog.open();
    }
}
