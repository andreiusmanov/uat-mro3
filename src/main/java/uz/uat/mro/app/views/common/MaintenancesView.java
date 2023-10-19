package uz.uat.mro.app.views.common;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.model.terms.common.Maintenance;
import uz.uat.mro.app.model.terms.common.services.MaintenanceService;

@PageTitle(value = "Виды работ")
@Route(value = "common/maintenances", layout = CommonLayout.class)
public class MaintenancesView extends VerticalLayout{
    private MaintenanceService service;
    private GridCrud<Maintenance> crud;
    private GridListDataView<Maintenance> dataView;
    private TextField searchField;
    private Button searchButton;

    /**
     * 
     */
    public MaintenancesView(MaintenanceService service) {
        this.service = service;
        grid();
        //add(new H3("Stations"), crud);
        add(crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(Maintenance.class);
        this.crud.getGrid().setColumns("code", "country.code3", "name");
        this.crud.getGrid().getColumnByKey("code").setHeader("Код");
        this.crud.getGrid().getColumnByKey("country.code3").setHeader("Страна");
        this.crud.getGrid().getColumnByKey("name").setHeader("Наименование");
        CrudFormFactory<Maintenance> factory = crud.getCrudFormFactory();
        factory.setVisibleProperties("country", "code", "name");
        factory.setFieldCaptions("Страна", "Код", "Наименование");

        factory.setFieldProvider("country", user -> {
            ComboBox<Country> countries = new ComboBox<>();
            countries.setItems(service.findAllCountries());
            countries.setItemLabelGenerator(e -> e.getShortName());
            return countries;
        });
        crud.setAddOperation(service::save);
        crud.setUpdateOperation(service::save);
        crud.setDeleteOperation(service::delete);
        crud.setFindAllOperation(service::findAll);

        this.dataView = crud.getGrid().getListDataView();
        this.searchField = new TextField("", "поиск");
        this.searchButton = new Button(VaadinIcon.SEARCH.create());
        crud.getCrudLayout().addFilterComponents(searchField, searchButton);

        searchButton.addClickListener(click -> {
            dataView.removeFilters();
            dataView.setFilter(maintenance -> maintenance.getCode().contains(searchField.getValue())
                    || maintenance.getName().contains(searchField.getValue()));
        });

        searchField.addValueChangeListener(event -> {
            if(searchField.getValue().isBlank()){
            dataView.removeFilters();
            }
        });


    }
}
