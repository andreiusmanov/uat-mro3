package uz.uat.mro.app.views.organization;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.views.MainLayout;

@PageTitle(value = "Список Организаций")
@Route(value = "organizations2", layout = MainLayout.class)
public class OrganizationsView2 extends VerticalLayout {
    private OrganizationService service;
    private GridCrud<OrganizationUnit> crud;
    private OrganizationUnit organization;
    private GridListDataView<OrganizationUnit> dataView;

    public OrganizationsView2(OrganizationService service) {
        this.service = service;
        grid();
        add(crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(OrganizationUnit.class);
        List<OrganizationUnit> units = service.findMainOrganizations("Организация");

        Grid<OrganizationUnit> grid = crud.getGrid();

        grid.getSelectionModel().addSelectionListener(selected -> {
            boolean res = !grid.getSelectedItems().isEmpty();
            this.organization = grid.getSelectionModel().getFirstSelectedItem().orElse(new OrganizationUnit());
        });
        grid.setItems(units);
        grid.setColumns("name", "code", "description");
        grid.getColumnByKey("name").setHeader("Наименование");
        grid.getColumnByKey("code").setHeader("Код");
        grid.getColumnByKey("description").setHeader("Описание");

        crud.setAddOperation(service::save);
        crud.setUpdateOperation(service::save);
        crud.setDeleteOperation(service::delete);
        crud.setFindAllOperation(() -> service.findMainOrganizations("Организации"));

        crud.getCrudFormFactory().setVisibleProperties("name", "code", "description", "shortName", "country", "type");
        crud.getCrudFormFactory().setFieldCaptions("Наименование", "Код", "Описание", "Аббрев.", "Страна", "Тип");
        crud.getCrudFormFactory().setFieldProvider("type", type -> {
            ComboBox<OrganizationUnitType> combo = new ComboBox<>("Тип", service.findAllOrganizationTypes());
            combo.setItemLabelGenerator((c) -> c.getName());
            return combo;
        });
        crud.getCrudFormFactory().setFieldProvider("country", type -> {
            ComboBox<Country> combo = new ComboBox<>("Страна", service.findAllCountries());
            combo.setItemLabelGenerator((c) -> c.getName() + " (" + c.getCode3()+")");
            return combo;
        });

    }
}