package uz.uat.mro.app.views.common;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitTypeService;
import uz.uat.mro.app.views.MainLayout;

@Route(value = "terms/unit-names", layout = MainLayout.class)
@PageTitle(value = "Типы структурных единиц предприятия")
public class OrganizationUnitNamesView extends VerticalLayout {
    private OrganizationUnitTypeService service;
    private GridCrud<OrganizationUnitType> crud;

    public OrganizationUnitNamesView(OrganizationUnitTypeService service) {
        super();
        this.service = service;
        crud();
        this.add(crud);
    }

    private void crud() {
        this.crud = new GridCrud<>(OrganizationUnitType.class);
        Grid<OrganizationUnitType> grid = crud.getGrid();

        grid.setColumns("name", "code", "description");
        grid.getColumnByKey("name").setHeader("Наименование");
        grid.getColumnByKey("code").setHeader("Код");
        grid.getColumnByKey("description").setHeader("Описание");

        crud.setAddOperation(service::save);
        crud.setUpdateOperation(service::save);
        crud.setDeleteOperation(service::delete);
        crud.setFindAllOperation(service::queryAllUnitNames);

        CrudFormFactory<OrganizationUnitType> factory = crud.getCrudFormFactory();
        factory.setVisibleProperties("name", "code", "description");
        factory.setFieldCaptions("Наименование", "Код", "Описание");

        factory.setFieldProvider("description", val -> {
            TextArea ta = new TextArea("Описание");
            return ta;
        });
        factory.setShowNotifications(true);
    }
}
