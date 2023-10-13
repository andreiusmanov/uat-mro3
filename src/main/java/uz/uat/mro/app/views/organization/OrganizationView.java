package uz.uat.mro.app.views.organization;

import com.google.common.collect.ImmutableList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.views.MainLayout;

@PageTitle(value = " Организация")
@Route(value = "common/organization", layout = MainLayout.class)
public class OrganizationView extends VerticalLayout {

    private OrganizationService service;
    private OrganizationUnit principle;
    private OrganizationUnit subordinate;

    public OrganizationView(OrganizationService service) {
        this.service = service;
        this.principle = new OrganizationUnit();
        principle.setName("principle");
        this.subordinate = new OrganizationUnit();
        subordinate.setName("subordinate");

        principle.setUnits(ImmutableList.of(subordinate));
        service.save(subordinate);
        service.save(principle);
    }

}
