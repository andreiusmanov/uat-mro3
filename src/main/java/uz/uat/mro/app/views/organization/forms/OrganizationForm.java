package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnitType;
import uz.uat.mro.app.model.terms.common.Country;

public class OrganizationForm extends FormLayout {
    private OrganizationService service;
    private final String typeName = "Организация";
    private ComboBox<Country> country;
    private TextField name;
    private TextField code;
    private TextArea description;
    private TextField shortName;
    private ComboBox<OrganizationUnitType> type;

    public OrganizationForm(boolean enabled, OrganizationService service) {
        super();
        this.service = service;
        data();
        this.add(country, name, code, shortName, type, description);
        this.setEnabled(enabled);
    }

    private void data() {
        this.country = new ComboBox<>("Страна", service.findAllCountries());
        this.country.setItemLabelGenerator((entry) -> entry.getName());
        this.name = new TextField("Наименование");
        this.code = new TextField("Код");
        this.shortName = new TextField("Аббревиатура");
        this.type = new ComboBox<>("Тип орг. структуры", service.findOrganizationType(typeName).get());
        this.type.setItemLabelGenerator((entry) -> entry.getName());
        this.description = new TextArea("Описание");
        this.setColspan(description, 2);
    }
}
