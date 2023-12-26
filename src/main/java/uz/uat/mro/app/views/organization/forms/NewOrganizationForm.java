package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.documents.organization.StructureService;
import uz.uat.mro.app.model.documents.organization.edges.HasOrganizationUnit;

public class NewOrganizationForm extends FormLayout {

    private StructureService service;
    private boolean isReadOnly;

    private final OrganizationUnit master;
    private OrganizationUnit subordinate;
    private HasOrganizationUnit hasUnit;
    private DatePicker startDate;
    private DatePicker endDate;
    private Checkbox active;

    private Binder<OrganizationUnit> binderUnit;
    private Binder<HasOrganizationUnit> binderEdge;

    private TextField name;
    private TextField code;
    private TextArea description;
    private TextField shortName;

    private ComboBox<OrganizationUnitType> type;

    public NewOrganizationForm(StructureService service, final OrganizationUnit master, boolean readOnly) {
        this.service = service;
        this.master = master;
        this.subordinate = new OrganizationUnit();
        this.hasUnit = new HasOrganizationUnit();
        form();
        binders();
        add(name, code, shortName, type, description, startDate, endDate, active);
        this.setColspan(description, 2);
    }

    private void binders() {
        this.binderUnit = new Binder<>(OrganizationUnit.class);
        this.binderEdge = new Binder<>(HasOrganizationUnit.class);

        binderUnit.setBean(subordinate);
        binderEdge.setBean(hasUnit);

        binderUnit.bindInstanceFields(this);
        binderEdge.bindInstanceFields(this);
    }

    private void form() {
        this.name = new TextField("Наименование");
        this.code = new TextField("Код");
        this.shortName = new TextField("Аббревиатура");
        this.type = new ComboBox<>("Тип орг. структуры", service.findAllTypes());
        this.type.setItemLabelGenerator((entry) -> entry.getName());
        this.description = new TextArea("Описание");
        setReadOnly();
        startDate = new DatePicker("Дата создания");
        endDate = new DatePicker("Дата отмены");
        active = new Checkbox();
    }

    private void setReadOnly() {
        name.setReadOnly(isReadOnly);
        code.setReadOnly(isReadOnly);
        shortName.setReadOnly(isReadOnly);
        type.setReadOnly(isReadOnly);
        description.setReadOnly(isReadOnly);
        startDate.setReadOnly(isReadOnly);
        endDate.setReadOnly(isReadOnly);
        active.setReadOnly(isReadOnly);
    }

    protected void save() {
        try {
            service.saveUnit(binderUnit.getBean());
            service.saveHasUnit(hasUnit);
            Notification.show("Запись " + subordinate.getName() + " сохранена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            throw e;
        }
    }

    protected void cancel() {
        try {
            service.saveUnit(binderUnit.getBean());
            service.saveHasUnit(hasUnit);
            Notification.show("Запись " + subordinate.getName() + " сохранена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            throw e;
        }
    }

}
