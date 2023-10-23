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
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;

@PageTitle(value = "Подразделения")
@Route(value = "organization/units", layout = OrganizationLayout.class)
public class OrganizationUnitsView extends VerticalLayout {
    private OrganizationService service;
    private OrganizationUnit organization;
    private Grid<HasOrganizationUnit> grid;
    private ComboBox<OrganizationUnitType> types;
    private MenuBar menu;
    private ComboBox<OrganizationUnit> organizationBox;
    private GridListDataView<HasOrganizationUnit> dataView;
    private RadioButtonGroup<String> radioGroup;
    private MenuItem viewItem;
    private MenuItem addItem;
    private MenuItem editItem;
    private MenuItem deleteItem;
    private MenuItem reportItem;
    private Dialog dialog;

    public OrganizationUnitsView(OrganizationService service) {
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        fields();
        crud();
        menu();
        radioGroup();

        add(new HorizontalLayout(organizationBox, types, radioGroup), menu, grid);

    }

    private void dialog() {
        this.dialog = new Dialog(null, null);
    }

    private void radioGroup() {
        this.radioGroup = new RadioButtonGroup<>("Статус");
        radioGroup.setItems("Активные", "Закрытые", "Все");
        radioGroup.setValue("Активные");
    }

    private void menu() {
        menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        viewItem = menu.addItem("Обзор");
        addItem = menu.addItem("Добавить");
        editItem = menu.addItem("Редактировать");
        deleteItem = menu.addItem("Удалить");
        reportItem = menu.addItem("Отчеты");

    }

    private void crud() {
        this.grid = new Grid<>(HasOrganizationUnit.class);
        this.grid.setItems(service.findOrganizationUnits(organization));
        this.grid.setColumns("name", "code", "shortName", "description");
        this.grid.getColumnByKey("name").setHeader("Наименование");
        this.grid.getColumnByKey("code").setHeader("Код");
        this.grid.getColumnByKey("shortName").setHeader("Аббрев.");
        this.grid.getColumnByKey("description").setHeader("Описание");

        // filter
        dataView = grid.getListDataView();

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
    }
}
