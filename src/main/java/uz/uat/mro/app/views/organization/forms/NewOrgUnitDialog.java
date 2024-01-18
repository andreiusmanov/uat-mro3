package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.StructureService;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;

public class NewOrgUnitDialog extends Dialog {
    private StructureService service;
    private NewOrganizationForm form;
    private NewHasOrganizationForm hasForm;
    private OrganizationUnit master;
    private Button saveButton;
    private Button cancelButton;

    /**
     * 
     * @param service
     * @param master
     * @param isReadOnly
     */
    public NewOrgUnitDialog(StructureService service, OrganizationUnit master) {
        super();
        this.service = service;
        this.master = master;
        this.setCloseOnEsc(true);
        form();
        hasForm();
        buttons();
        header();
        setCloseOnEsc(true);
        this.getFooter().add(saveButton, cancelButton);
        add(form, hasForm);
    }

    private void header() {
        Button close = new Button(VaadinIcon.CLOSE.create(), click -> {
            this.close();
        });
        this.setHeaderTitle("Новое подразделение в составе " + master.getShortName());
        this.getHeader().add(close);
    }

    private void form() {
        this.form = new NewOrganizationForm(service);
    }

    private void hasForm() {
        this.hasForm = new NewHasOrganizationForm();
    }

    private void buttons() {
        this.saveButton = new Button("Сохранить");
        saveButton.addClickListener(click -> {
            OrganizationUnit subordinate = form.getUnit();
            subordinate.setCountry(master.getCountry());
            HasOrganizationUnit edge = hasForm.getEdge();
            service.saveNewUnitAndEdge(master, subordinate, edge);
            this.close();
        });
        this.cancelButton = new Button("Отменить");
        cancelButton.addClickListener(click -> {
            this.close();
        });
    }

}
