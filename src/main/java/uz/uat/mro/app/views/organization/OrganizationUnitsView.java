package uz.uat.mro.app.views.organization;

import com.google.common.collect.ImmutableList;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;
import uz.uat.mro.app.views.organization.forms.OrganizationUnitDialog;

@PageTitle(value = "Подразделения")
@Route(value = "organization/units", layout = OrganizationLayout.class)
public class OrganizationUnitsView extends VerticalLayout {
    private OrganizationService service;
    private OrganizationUnit organization;
    private OrganizationUnit selectedUnit;
    private Grid<HasOrganizationUnit> grid;
    private HasOrganizationUnit hasUnit;
    private ComboBox<OrganizationUnitType> types;
    private MenuBar menu;
    private ComboBox<OrganizationUnit> organizationBox;
    private ComboBox<OrganizationUnit> selectedBox;
    private GridListDataView<HasOrganizationUnit> dataView;
    private Dialog dialog;

    public OrganizationUnitsView(OrganizationService service) {
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        fields();
        grid();
        menu();
        add(new HorizontalLayout(organizationBox, selectedBox, types), menu, grid);

    }

    private void dialog() {
        this.dialog = new OrganizationUnitDialog(service, organization, false);
        dialog.addDialogCloseActionListener(event -> {
            grid.getDataProvider().refreshAll();
        });
        this.dialog.open();
    }

    private void dialogNew() {
        this.dialog = new OrganizationUnitDialog(service, organization, false);
        dialog.addDialogCloseActionListener(event -> {
            grid.getDataProvider().refreshAll();
        });
        this.dialog.open();
    }

    private void menu() {
        menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        MenuItem viewItem = menu.addItem("Обзор", click -> {
            dialog();
            dialog.open();
        });

        MenuItem addItem = menu.addItem("Добавить", click -> {
            dialogNew();
        });
        MenuItem editItem = menu.addItem("Редактировать");
        MenuItem deleteItem = menu.addItem("Удалить");
        MenuItem reportItem = menu.addItem("Отчеты");

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

        HasOrganizationUnit emptyUnit = new HasOrganizationUnit();
        emptyUnit.setMaster(organization);
        this.hasUnit = emptyUnit;

        // filter
        dataView = grid.getListDataView();

        // listener
        grid.getSelectionModel().addSelectionListener(selected -> {

            boolean res = grid.getSelectionModel().getFirstSelectedItem().isPresent();
            if (res) {
                this.hasUnit = grid.getSelectionModel().getFirstSelectedItem().get();
                this.selectedUnit = hasUnit.getMaster();
            } else {
                HasOrganizationUnit emptyHasUnit = new HasOrganizationUnit();
                emptyHasUnit.setMaster(organization);
            }
        });
    }

    private void fields() {
        this.organizationBox = new ComboBox<>("Организация", ImmutableList.of(organization));
        this.organizationBox.setValue(organization);
        this.organizationBox.setItemLabelGenerator(item -> organization.getShortName());
        this.organizationBox.setReadOnly(true);
        this.types = new ComboBox<>("Тип Структуры", service.findAllOrganizationTypes());
        this.types.setItemLabelGenerator(item -> item.getName());

        types.addValueChangeListener(change -> {
            dataView.setFilter(t -> t.getSubordinate().getType().equals(types.getValue()));
        });
        this.selectedBox = new ComboBox<>("Выбранное подразделение", service.findSubordinates(organization));
    }

}
