package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.documents.organization.StructureService;

public class NewOrganizationForm extends FormLayout {

    private StructureService service;

    private OrganizationUnit subordinate;

    private Binder<OrganizationUnit> binderUnit;

    private TextField name;
    private TextField code;
    private TextArea description;
    private TextField shortName;

    private ComboBox<OrganizationUnitType> type;

    public NewOrganizationForm(StructureService service) {
        this.service = service;
        this.subordinate = new OrganizationUnit();
        form();
        binders();
        add(name, code, shortName, type, description);
        this.setColspan(description, 2);
    }

    private void binders() {
        this.binderUnit = new Binder<>(OrganizationUnit.class);
        binderUnit.setBean(subordinate);
        binderUnit.bindInstanceFields(this);
    }

    private void form() {
        this.name = new TextField("Наименование");
        this.code = new TextField("Код");
        this.shortName = new TextField("Аббревиатура");
        this.type = new ComboBox<>("Тип орг. структуры", service.findAllTypes());
        this.type.setItemLabelGenerator((entry) -> entry.getName());
        this.description = new TextArea("Описание");
    }

    protected OrganizationUnit save() {
        try {
            OrganizationUnit subordinate = service.saveUnit(binderUnit.getBean());
            Notification.show("Запись " + subordinate.getName() + " сохранена", 5000, Position.MIDDLE);
            return subordinate;
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
