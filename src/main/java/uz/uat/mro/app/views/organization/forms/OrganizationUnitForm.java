package uz.uat.mro.app.views.organization.forms;

import java.time.LocalDate;
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
    private DatePicker dateStart;
    private DatePicker dateEnd;
    private Checkbox active;

    public OrganizationUnitForm(boolean readOnly, OrganizationService service) {
        super();
        this.service = service;
        data();
        this.add(name, code, shortName, type, description,
                dateStart, dateEnd, active);
        this.setReadOnly(readOnly);
    }

    private void data() {
        this.name = new TextField("Наименование");
        this.code = new TextField("Код");
        this.shortName = new TextField("Аббревиатура");
        this.type = new ComboBox<>("Тип подразделения", service.findAllOrganizationTypes());
        this.type.setItemLabelGenerator((entry) -> entry.getName());
        this.description = new TextArea("Описание");

        this.dateStart = new DatePicker(LocalDate.now(), Locale.forLanguageTag("ru-RU"));
        this.dateEnd = new DatePicker();
        dateStart.setLabel("Дата Открытия");
        dateEnd.setLabel("Дата Закрытия");
        dateEnd.setLocale(Locale.forLanguageTag("ru-RU"));
        this.active = new Checkbox("Действующее подразделение");
        this.setColspan(description, 2);
    }

    private void setReadOnly(boolean readOnly) {
        name.setReadOnly(readOnly);
        code.setReadOnly(readOnly);
        shortName.setReadOnly(readOnly);
        type.setReadOnly(readOnly);
        description.setReadOnly(readOnly);
        dateStart.setReadOnly(readOnly);
        dateEnd.setReadOnly(readOnly);
        active.setReadOnly(readOnly);
    }

}
