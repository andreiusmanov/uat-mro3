package uz.uat.mro.app.views.employee;

import com.google.common.collect.ImmutableList;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;

import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.model.documents.staff.Position;

public class EmployeeForm extends FormLayout {

    private ComboBox<OrganizationUnit> organization;
    private TextField name;
    private TextField surname;
    private TextField patronymic;
    private TextField tabel;
    private Grid<Position> positions;

    public EmployeeForm(OrganizationUnit org) {
        setup(org);
        add(organization, name, surname, patronymic, tabel, positions);
    }

    private void setup(OrganizationUnit org) {
        this.organization = new ComboBox<>("Организация", ImmutableList.of(org));
        organization.setValue(org);
        this.name = new TextField("Имя");
        this.surname = new TextField("Фамилия");
        this.patronymic = new TextField("Отчество");
        this.tabel = new TextField("Табельный номер");
        this.positions = new Grid<>(Position.class);
    }

}
