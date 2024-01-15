package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import uz.uat.mro.app.model.documents.organization.OrganizationStructure;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.StructureService;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
import uz.uat.mro.app.views.organization.OrganizationStructuresView;

public class StructureListView extends VerticalLayout {

    private StructureService service;
    private MenuBar menu;
    private MenuItem findItem;
    private MenuItem addItem;
    private MenuItem editItem;
    private MenuItem deleteItem;
    private Grid<HasOrganizationUnit> grid;
    private OrganizationStructure structure;
    private OrganizationUnit organization;
    private OrganizationUnit selectedUnit;
    private HasOrganizationUnit hasUnit;
    private GridListDataView<HasOrganizationUnit> dataView;
    private H3 header;

    public StructureListView(StructureService service, OrganizationStructure structure) {
        this.service = service;
        this.structure = structure;
        this.organization = structure.getOrganization();
        this.selectedUnit = organization;
        header();
        menu();
        grid();
        add(header, menu, grid);
    }

    private void header() {
        header = new H3("Орг структура в виде списка");
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        menu.addItem(VaadinIcon.HOME_O.create(), click -> {
            UI.getCurrent().navigate(OrganizationStructuresView.class);
        });

        findItem = menu.addItem("Показать", "Показать орг. структуру", click -> {
            NewOrgUnitDialog newDialog = newDialog();
            newDialog.open();
            Notification.show("Показать");
        });
        addItem = menu.addItem("Добавить", "Добавить орг. структуру", click -> {
            NewOrgUnitDialog newDialog = newDialog();
            newDialog.open();
            Notification.show("Клик кнопка Добавить");
        });

        editItem = menu.addItem("Редактировать", "Редактировать орг. структуру", click -> {
            NewOrgUnitDialog newDialog = newDialog();
            newDialog.open();
            Notification.show("Редактировать");
        });
        deleteItem = menu.addItem("Удалить", "Удалить орг. структуру", click -> {
            Notification.show("Удалить");
        });

        this.findItem.setEnabled(false);
        this.editItem.setEnabled(false);
        this.deleteItem.setEnabled(false);

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

        // listener
        grid.getSelectionModel().addSelectionListener(event -> {
            boolean res = !grid.getSelectedItems().isEmpty();
            enabledMenuItems(res);
            if (res) {
                this.hasUnit = grid.getSelectionModel().getFirstSelectedItem().get();
                this.selectedUnit = hasUnit.getSubordinate();
            } else {
                this.selectedUnit = organization;
            }
        });
    }

    private NewOrgUnitDialog newDialog() {
        NewOrgUnitDialog newDialog = new NewOrgUnitDialog(service, selectedUnit);
        newDialog.addDialogCloseActionListener(event -> {
            grid.getDataProvider().refreshAll();
        });
        return newDialog;
    }

    private void enabledMenuItems(boolean enable) {
        this.findItem.setEnabled(enable);
        this.editItem.setEnabled(enable);
        this.deleteItem.setEnabled(enable);
    }
}
