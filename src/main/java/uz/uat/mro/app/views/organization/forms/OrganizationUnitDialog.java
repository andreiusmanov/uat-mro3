package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;

public class OrganizationUnitDialog extends Dialog {
    private OrganizationService service;
    private OrganizationUnitForm form;
    private OrganizationUnit master;
    private OrganizationUnit subordinate;
    private HasOrganizationUnit hasUnit;
    private Binder<HasOrganizationUnit> binder;
    private Binder<OrganizationUnit> binder2;
    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;
    private boolean readOnly;
    private boolean isEdit = false;

    /**
     * конструктор для редактирования и удаления связки между master и subordinate
     * units
     * 
     * @param service
     * @param hasUnit
     */
    private OrganizationUnitDialog(OrganizationService service, HasOrganizationUnit hasUnit) {
        super();

        this.service = service;
        this.master = hasUnit.getMaster();
        this.subordinate = new OrganizationUnit();
        this.hasUnit = hasUnit;
        this.setCloseOnEsc(true);
        this.getHeader().add(new Button(VaadinIcon.CLOSE.create(), click -> {
            this.close();
        }));
        form();
        data();
        buttons();
        isEdit = true;
        setCloseOnEsc(true);
        add(form, new HorizontalLayout(saveButton, cancelButton, deleteButton));
    }

    /**
     * конструктор для редактирования/просмотра subordinate unit
     * 
     * @param service
     */
    private OrganizationUnitDialog(OrganizationService service, OrganizationUnit organization, boolean readOnly) {
        super();
        this.service = service;
        this.subordinate = organization;
        this.setCloseOnEsc(true);
        header();
        form();
        data();
        buttons();
        isEdit = !readOnly;
        add(form, new HorizontalLayout(saveButton, cancelButton));
    }

    private void header() {
        H3 text = new H3("Подразделение " + subordinate.getShortName());
        Button close = new Button(VaadinIcon.CLOSE.create(), click -> {
            this.close();
        });

        HorizontalLayout h = new HorizontalLayout();
        h.add(text, close);
        h.setAlignItems(FlexComponent.Alignment.STRETCH);
        this.getHeader().add(h);

    }

    private void form() {
        this.form = new OrganizationUnitForm(readOnly, service);
    }

    public static OrganizationUnitDialog editOrganizationUnit(OrganizationService service,
            OrganizationUnit organization,
            boolean readOnly) {
        return new OrganizationUnitDialog(service, organization, readOnly);
    }

    public static OrganizationUnitDialog createOrganizationUnit(OrganizationService service,
            HasOrganizationUnit hasUnit) {
        return new OrganizationUnitDialog(service, hasUnit);
    }

    private void buttons() {
        this.saveButton = new Button("Сохранить");
        saveButton.addClickListener(click -> save());
        this.cancelButton = new Button("Отменить");
        cancelButton.addClickListener(click -> cancel());
        this.deleteButton = new Button("Удалить");
        deleteButton.addClickListener(click -> delete());

    }

    private void delete() {
        try {
            service.delete(subordinate);
            service.deleteHasUnit(hasUnit);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void save() {
        try {
            service.save(binder2.getBean());
            if (!isEdit) {
                service.saveHasUnit(binder.getBean());
            }
            Notification.show("Запись " + subordinate.getName() + " сохранена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            throw e;
        }
    }

    private void cancel() {
        try {
            binder2.setBean(service.getOrganizationUnitById(subordinate.getArangoId()));
            if (!isEdit) {
                binder.setBean(service.findHasOrganizationUnitById(hasUnit));
            }
            Notification.show("Запись " + subordinate.getName() + " отменена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            Notification.show(e.getMessage());
        }
    }

    private void data() {
        this.binder = new Binder<>(HasOrganizationUnit.class);
        binder.setBean(hasUnit);
        this.binder2 = new Binder<>(OrganizationUnit.class);
        binder2.setBean(subordinate);
        binder2.bindInstanceFields(form);
    }

}
