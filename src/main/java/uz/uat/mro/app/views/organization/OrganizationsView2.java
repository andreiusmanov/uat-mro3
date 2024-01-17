package uz.uat.mro.app.views.organization;

import org.vaadin.crudui.crud.impl.GridCrud;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;
import uz.uat.mro.app.views.MainLayout;

@PageTitle(value = "Список Организаций")
@Route(value = "data/organizations2", layout = MainLayout.class)
public class OrganizationsView2 extends VerticalLayout {
    private OrganizationService service;
    private GridCrud<OrganizationUnit> crud;
    private OrganizationUnit organization;
    private Button viewButton;

    public OrganizationsView2(OrganizationService service) {
        this.service = service;
        grid();
        add(crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(OrganizationUnit.class);

        Grid<OrganizationUnit> grid = crud.getGrid();
        crud.setFindAllOperation(()-> service.f());
    }

    private void button() {
        this.viewButton = new Button("Обзор");
        this.viewButton.setEnabled(false);
        viewButton.addClickListener(click -> {
            MyUtils.setAttribute(Keys.ORGANIZATION, organization);
            UI.getCurrent().navigate(OrganizationView.class);
        });
    }

}
