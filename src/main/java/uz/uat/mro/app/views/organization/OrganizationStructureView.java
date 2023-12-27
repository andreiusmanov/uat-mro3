package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationStructure;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.StructureService;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;
import uz.uat.mro.app.views.organization.forms.StructureFlowchartView;
import uz.uat.mro.app.views.organization.forms.StructureListView;

@PageTitle(value = "Орг. Структура")
@Route(value = "org-structure")
public class OrganizationStructureView extends VerticalLayout {
    private StructureService service;
    private OrganizationStructure structure;
    private TabSheet tabSheet;
    private OrganizationUnit organization;

    public OrganizationStructureView(StructureService service) {
        this.service = service;
        this.structure = (OrganizationStructure) MyUtils.getAttribute(Keys.STRUCTURE);
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        tabs();
        add(tabSheet);
    }

    private void tabs() {
        this.tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        tabSheet.add("Список", new StructureListView(service, organization));
        tabSheet.add("Схема", new StructureFlowchartView());
    }
}
