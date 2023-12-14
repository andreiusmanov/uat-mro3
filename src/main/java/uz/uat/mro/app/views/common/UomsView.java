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

import uz.uat.mro.app.model.terms.common.Uom;
import uz.uat.mro.app.model.terms.common.UomType;
import uz.uat.mro.app.model.terms.common.services.UomService;
import uz.uat.mro.app.views.MainLayout;

@PageTitle(value = "Ед. измерения")
@Route(value = "common/uoms", layout = MainLayout.class)
public class UomsView extends VerticalLayout {
    private UomService service;
    private GridCrud<Uom> crud;
    private GridListDataView<Uom> dataView;
    private TextField searchField;
    private Button searchButton;

    /**
     * 
     */
    public UomsView(UomService service) {
        this.service = service;
        grid();
        add(crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(Uom.class);
        this.crud.getGrid().setColumns("name", "code", "description", "type.name");
        this.crud.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.crud.getGrid().getColumnByKey("code").setHeader("Код");
        this.crud.getGrid().getColumnByKey("description").setHeader("Описание");
        this.crud.getGrid().getColumnByKey("type.name").setHeader("Тип");

        crud.setAddOperation(service::saveUom);
        crud.setUpdateOperation(service::saveUom);
        crud.setDeleteOperation(service::deleteUom);
        crud.setFindAllOperation(service::findAllUoms);

        this.dataView = crud.getGrid().getListDataView();
        this.searchField = new TextField("", "поиск");
        this.searchButton = new Button(VaadinIcon.SEARCH.create());
        crud.getCrudLayout().addFilterComponents(searchField, searchButton);

        searchButton.addClickListener(click -> {
            dataView.removeFilters();
            dataView.setFilter(
                    uomType -> uomType.getCode().toLowerCase().contains(searchField.getValue().toLowerCase()));
        });

        searchField.addValueChangeListener(event -> {
            if (searchField.getValue().isBlank()) {
                dataView.removeFilters();
            }
        });

        CrudFormFactory<Uom> factory = crud.getCrudFormFactory();
        factory.setVisibleProperties("name", "code", "description", "type");
        factory.setFieldCaptions("Наименование", "Код", "Описание","Тип");

        factory.setFieldProvider("type", element -> {
            ComboBox<UomType> c = new ComboBox<>("Тип", service.findAllUomTypes());
            c.setItemLabelGenerator(e -> e.getName());
            return c;
        });


    }
}
