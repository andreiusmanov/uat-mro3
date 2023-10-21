package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.UatUtils;


@PageTitle("Организация")
@Route(value = "structure/organization", layout = OrganizationLayout.class)
public class OrganizationView extends VerticalLayout {

    public OrganizationUnit organization;

    public OrganizationView() {
        super();
        this.organization = (OrganizationUnit) UatUtils.getAttribute(Keys.PROJECT);


    }

}
