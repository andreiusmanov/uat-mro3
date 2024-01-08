package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;

public class NewHasOrganizationForm extends FormLayout {

    private HasOrganizationUnit hasUnit;
    private DatePicker dateStart;
    private DatePicker dateEnd;
    private Checkbox active;

    private Binder<HasOrganizationUnit> binderEdge;

    public NewHasOrganizationForm() {
        this.hasUnit = new HasOrganizationUnit();
        form();
        binder();
        add(dateStart, dateEnd, active);
    }

    private void binder() {
        this.binderEdge = new Binder<>(HasOrganizationUnit.class);
        binderEdge.setBean(hasUnit);
        binderEdge.bindInstanceFields(this);
    }

    private void form() {
        dateStart = new DatePicker("Дата создания");
        dateEnd = new DatePicker("Дата отмены");
        active = new Checkbox("Действующий");
    }

    public HasOrganizationUnit getEdge() {
        return binderEdge.getBean();
    }
}
