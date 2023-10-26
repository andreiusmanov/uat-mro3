package uz.uat.mro.app.views.organization.forms;

import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
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
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    /**
     * Конструктор новый отдел, новыя связь с master подразделением/организацией
     * 
     * @param service
     * @param hasUnit
     */

    /**
     * конструктор для редактирования и удаления связки между master и subordinate
     * units
     * 
     * @param service
     * @param hasUnit
     */
    public OrganizationUnitDialog(OrganizationService service, HasOrganizationUnit hasUnit) {
        super();
        
        this.service = service;
        this.master = hasUnit.getMaster();
        this.subordinate = new OrganizationUnit();
        this.setCloseOnEsc(true);
        this.getHeader().add(new Button(VaadinIcon.CLOSE.create(), click -> {
            this.close();
        }));
        form();
        data();
        buttons();
        add(form, new HorizontalLayout(saveButton, cancelButton, deleteButton));
    }
/**
     * конструктор для создания связки между master и subordinate
     * units
     * 
     * @param service
     * @param hasUnit
     */
    public OrganizationUnitDialog(OrganizationService service, OrganizationUnit organization) {
        super();
        //this.getHeader().add(new H3("Подразделение " + master.getShortName()));
        this.service = service;
        this.master = organization;
        this.subordinate = new OrganizationUnit();
        this.hasUnit = new HasOrganizationUnit();
        hasUnit.setMaster(master);
        hasUnit.setSubordinate(subordinate);
        this.setCloseOnEsc(true);
        this.getHeader().add(new H3("Подразделение " + master.getShortName()));
        this.getHeader().add(new Button(VaadinIcon.CLOSE.create(), click -> {
            this.close();
        }));
        form();
        data();
        buttons();
        add(form, new HorizontalLayout(saveButton, cancelButton, deleteButton));
    }

    private void form() {
        this.form = new OrganizationUnitForm(true, service);
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
            service.saveHasUnit(binder.getBean());
            Notification.show("Запись " + subordinate.getName() + " сохранена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    private void cancel() {
        try {
            binder2.setBean(service.getOrganizationUnitById(subordinate.getArangoId()));
            binder.setBean(service.findHasOrganizationUnitById(hasUnit));
            Notification.show("Запись " + subordinate.getName() + " отменена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    private void data() {
        this.binder = new Binder<>(HasOrganizationUnit.class);
        binder.setBean(hasUnit);
        binder.bindInstanceFields(form);

        this.binder2 = new Binder<>(OrganizationUnit.class);
        binder2.setBean(subordinate);
        binder2.bindInstanceFields(form);
    }

}
