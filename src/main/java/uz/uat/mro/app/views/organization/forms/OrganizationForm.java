package uz.uat.mro.app.views.organization.forms;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.UatUtils;

public class OrganizationForm extends FormLayout {

    private OrganizationService service;
    private OrganizationUnit organization;
    private ComboBox<Country> country;
    private TextField name;
    private TextField code;
    private TextArea description;
    private TextField shortName;
    private TextField type;

    public OrganizationForm(OrganizationService service) {
        super();
        this.service = service;
        this.organization = (OrganizationUnit) UatUtils.getAttribute(Keys.PROJECT);

        this.add(country, name, code, description, shortName, type);
    }

}
