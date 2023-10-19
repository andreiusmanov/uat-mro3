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
import uz.uat.mro.app.model.terms.common.Station;
import uz.uat.mro.app.model.terms.common.services.StationService;

@PageTitle(value = "Станции")
@Route(value = "common/stations", layout = CommonLayout.class)
public class StationsView extends VerticalLayout {
    private StationService service;
    private GridCrud<Station> crud;
    private GridListDataView<Station> dataView;
    private TextField searchField;
    private Button searchButton;

    /**
     * 
     */
    public StationsView(StationService service) {
        this.service = service;
        grid();
        //add(new H3("Stations"), crud);
        add(crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(Station.class);
        this.crud.getGrid().setColumns("code", "country.code3", "name");
        this.crud.getGrid().getColumnByKey("code").setHeader("Код");
        this.crud.getGrid().getColumnByKey("country.code3").setHeader("Страна");
        this.crud.getGrid().getColumnByKey("name").setHeader("Наименование");
        CrudFormFactory<Station> factory = crud.getCrudFormFactory();
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
        crud.setFindAllOperation(service::findAllStations);

        this.dataView = crud.getGrid().getListDataView();
        this.searchField = new TextField("", "поиск");
        this.searchButton = new Button(VaadinIcon.SEARCH.create());
        crud.getCrudLayout().addFilterComponents(searchField, searchButton);

        searchButton.addClickListener(click -> {
            dataView.removeFilters();
            dataView.setFilter(station -> station.getCode().contains(searchField.getValue())
                    || station.getName().contains(searchField.getValue()));
        });

        searchField.addValueChangeListener(event -> {
            dataView.removeFilters();
        });


    }
}
