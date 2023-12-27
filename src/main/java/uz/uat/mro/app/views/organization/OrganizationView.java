package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;
import uz.uat.mro.app.views.organization.forms.OrganizationForm;

@PageTitle("Организация")
@Route(value = "structure/organization", layout = OrganizationLayout.class)
public class OrganizationView extends VerticalLayout {
    private OrganizationService service;
    public OrganizationUnit organization;
    private OrganizationForm form;
    private Binder<OrganizationUnit> binder;

    public OrganizationView(OrganizationService service) {
        super();
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        form();
        data();
        add(form);
    }

    private void form() {
        this.form = new OrganizationForm(service);
        this.form.setReadOnly(true);
    }

    private void data() {
        this.binder = new Binder<>(OrganizationUnit.class);
        binder.setBean(organization);
        binder.setReadOnly(true);
        binder.bindInstanceFields(form);
    }

}
