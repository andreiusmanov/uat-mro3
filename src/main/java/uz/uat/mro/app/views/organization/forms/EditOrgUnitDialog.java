package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.StructureService;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;

public class EditOrgUnitDialog extends Dialog {
    private StructureService service;
    private EditOrganizationForm form;
    private EditHasOrganizationForm hasForm;
    private HasOrganizationUnit hasUnit;
    private OrganizationUnit unit;
    private Button saveButton;
    private Button cancelButton;

    /**
     * 
     * @param service
     * @param hasUnit
     * @param isReadOnly
     */
    public EditOrgUnitDialog(StructureService service, HasOrganizationUnit hasUnit) {
        super();
        this.service = service;
        this.hasUnit = hasUnit;
        this.unit = hasUnit.getSubordinate();
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
        this.setHeaderTitle("Подразделение " + unit.getShortName());
        this.getHeader().add(close);
    }

    private void form() {
        this.form = new EditOrganizationForm(service, unit);
    }

    private void hasForm() {
        this.hasForm = new EditHasOrganizationForm(hasUnit);
    }

    private void buttons() {
        this.saveButton = new Button("Сохранить");
        saveButton.addClickListener(click -> {
            OrganizationUnit subordinate = form.getUnit();
            HasOrganizationUnit edge = hasForm.getEdge();
            service.saveUnit(subordinate);
            service.saveHasUnit(edge);
            this.close();
        });
        this.cancelButton = new Button("Отменить");
        cancelButton.addClickListener(click -> {
            this.close();
        });
    }

}
