package uz.uat.mro.app.views.organization.forms;

import java.util.Locale;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;

public class OrganizationUnitForm extends FormLayout {
    private OrganizationService service;
    private TextField name;
    private TextField code;
    private TextArea description;
    private TextField shortName;
    private ComboBox<OrganizationUnitType> type;
    private DatePicker startDate;
    private DatePicker endDate;
    private Checkbox active;



    

    public OrganizationUnitForm(OrganizationService service, boolean readOnly ) {
        super();
        this.service = service;
        data();
        this.add(name, code, shortName, type, description,
                startDate, endDate, active);
        this.setReadOnly(readOnly);
    }

    private void data() {
        this.name = new TextField("Наименование");
        this.code = new TextField("Код");
        this.shortName = new TextField("Аббревиатура");
        this.type = new ComboBox<>("Тип подразделения", service.findAllOrganizationTypes());
        this.type.setItemLabelGenerator((entry) -> entry.getName());
        this.description = new TextArea("Описание");

        this.startDate = new DatePicker();
        startDate.setLabel("Дата Открытия");
        startDate.setLocale(Locale.forLanguageTag("ru-RU"));

        this.endDate = new DatePicker();
        endDate.setLabel("Дата Закрытия");
        endDate.setLocale(Locale.forLanguageTag("ru-RU"));
        this.active = new Checkbox("Действующее подразделение");
        this.setColspan(description, 2);
    }

    private void setReadOnly(boolean readOnly) {
        // name.setReadOnly(readOnly);
        // code.setReadOnly(readOnly);
        // shortName.setReadOnly(readOnly);
        // type.setReadOnly(readOnly);
        // description.setReadOnly(readOnly);
        // startDate.setReadOnly(readOnly);
        // endDate.setReadOnly(readOnly);
        // active.setReadOnly(readOnly);

        this.setReadOnly(readOnly);
    }

}
