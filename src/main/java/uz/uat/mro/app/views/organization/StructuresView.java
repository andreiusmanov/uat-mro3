package uz.uat.mro.app.views.organization;

import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationStructure;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;

@PageTitle(value = "Орг. Структура")
@Route(value = "organization/structure", layout = OrganizationLayout.class)
public class StructuresView extends VerticalLayout {
    private OrganizationService service;
    private GridCrud<OrganizationStructure> crud;
    private OrganizationUnit organization;
    private OrganizationStructure structure;
    private Button reviewButton;

    public StructuresView(OrganizationService service) {
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        button();
        crud();
        add(crud);
    }

    private void button() {
        this.reviewButton = new Button("Обзор");
        this.reviewButton.addClickListener(click -> {
            MyUtils.setAttribute(Keys.STRUCTURE, structure);
            UI.getCurrent().navigate(OrganizationStructureView.class);
        });
    }

    private void crud() {
        this.crud = new GridCrud<>(OrganizationStructure.class);
        Grid<OrganizationStructure> grid = crud.getGrid();
        grid.setColumns("organization.shortName", "startDate", "endDate", "active");
        grid.getColumnByKey("organization.shortName").setHeader("Организация");
        grid.getColumnByKey("startDate").setHeader("Дата создания");
        grid.getColumnByKey("endDate").setHeader("Дата отмены");
        grid.getColumnByKey("active").setHeader("Действующая");
        grid.getSelectionModel().addSelectionListener(event -> {
            this.structure = grid.getSelectionModel().getFirstSelectedItem().orElse(null);

        });

        crud.setAddOperation(service::saveStructure);
        crud.setUpdateOperation(service::saveStructure);
        crud.setDeleteOperation(service::deleteStructure);
        crud.setFindAllOperation(() -> service.findAllStructures(organization));
        crud.getCrudLayout().addToolbarComponent(reviewButton);

        CrudFormFactory<OrganizationStructure> factory = crud.getCrudFormFactory();

        factory.setNewInstanceSupplier(() -> {
            OrganizationStructure s = new OrganizationStructure();
            s.setOrganization(organization);
            return s;
        });
        factory.buildCaption(CrudOperation.UPDATE, structure);
        factory.buildCaption(CrudOperation.DELETE, structure);
        factory.setVisibleProperties("startDate", "endDate", "active");
    }
}
