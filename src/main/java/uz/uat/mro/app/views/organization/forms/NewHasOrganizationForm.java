package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.StructureService;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;

public class NewHasOrganizationForm extends FormLayout {

    private StructureService service;

    private HasOrganizationUnit hasUnit;
    private DatePicker startDate;
    private DatePicker endDate;
    private Checkbox active;

    private Binder<HasOrganizationUnit> binderEdge;

    public NewHasOrganizationForm(StructureService service) {
        this.service = service;
        this.hasUnit = new HasOrganizationUnit();
        form();
        binder();
        add(startDate, endDate, active);
    }

    private void binder() {
        this.binderEdge = new Binder<>(HasOrganizationUnit.class);
        binderEdge.setBean(hasUnit);
        binderEdge.bindInstanceFields(this);
    }

    private void form() {
        startDate = new DatePicker("Дата создания");
        endDate = new DatePicker("Дата отмены");
        active = new Checkbox();
    }

    protected void save(OrganizationUnit master, OrganizationUnit subordinate) {
        try {
            this.hasUnit.setMaster(master);
            this.hasUnit.setSubordinate(subordinate);
            service.saveHasUnit(hasUnit);
            Notification.show("Запись " + subordinate.getName() + " сохранена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            throw e;
        }
    }

    protected void cancel() {
        try {
            Notification.show("Запись не сохранена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            throw e;
        }
    }

}
