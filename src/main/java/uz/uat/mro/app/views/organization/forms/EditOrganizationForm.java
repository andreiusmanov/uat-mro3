package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.documents.organization.StructureService;

public class EditOrganizationForm extends FormLayout {

    private StructureService service;
    private OrganizationUnit subordinate;
    private Binder<OrganizationUnit> binderUnit;
    private TextField name;
    private TextField code;
    private TextArea description;
    private TextField shortName;
    private ComboBox<OrganizationUnitType> type;

    public EditOrganizationForm(StructureService service, OrganizationUnit unit) {
        this.service = service;
        this.subordinate = unit;
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
        this.type.setItemLabelGenerator((entry) -> entry.getCode());
        this.description = new TextArea("Описание");
    }

    public OrganizationUnit getUnit() {
        return binderUnit.getBean();
    }
}
