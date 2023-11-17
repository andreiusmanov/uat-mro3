package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.StructureService;
@PageTitle(value = "Орг. Структура")
@Route(value = "org-structure")
public class OrganizationStructureView extends VerticalLayout{
    private StructureService service;
    private Tab editTab;


    
    public OrganizationStructureView(StructureService service) {
        this.service = service;
    }



    private void tabs(){
        this.editTab = new Tab();
        editTab.add("null");
    }
}
