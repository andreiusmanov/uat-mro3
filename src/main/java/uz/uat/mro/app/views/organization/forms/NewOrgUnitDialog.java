package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.StructureService;

public class NewOrgUnitDialog extends Dialog {
    private StructureService service;
    private NewOrganizationForm form;
    private OrganizationUnit master;
    private Button saveButton;
    private Button cancelButton;
    private boolean readOnly;

    /**
     * конструктор для редактирования и удаления связки между master и subordinate
     * units
     * 
     * @param service
     * @param hasUnit
     */
    public NewOrgUnitDialog(StructureService service, OrganizationUnit master, boolean isReadOnly) {
        super();
        this.service = service;
        this.master = master;
        this.setCloseOnEsc(true);
        this.getHeader().add(new Button(VaadinIcon.CLOSE.create(), click -> {
            this.close();
        }));
        form();
        buttons();
        header();
        setCloseOnEsc(true);
        add(form, new HorizontalLayout(saveButton, cancelButton));
    }

    private void header() {
        H3 text = new H3("Новое подразделение в составе " + master.getShortName());
        Button close = new Button(VaadinIcon.CLOSE.create(), click -> {
            this.close();
        });

        HorizontalLayout h = new HorizontalLayout();
        h.add(text, close);
        h.setAlignItems(FlexComponent.Alignment.STRETCH);
        this.getHeader().add(h);

    }

    private void form() {
        this.form = new NewOrganizationForm(service, master, readOnly);
    }

    private void buttons() {
        this.saveButton = new Button("Сохранить");
        saveButton.addClickListener(click -> form.save());
        this.cancelButton = new Button("Отменить");
        cancelButton.addClickListener(click -> form.cancel());
    }

}
