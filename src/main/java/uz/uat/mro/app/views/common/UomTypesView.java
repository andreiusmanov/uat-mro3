package uz.uat.mro.app.views.common;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.terms.common.UomType;
import uz.uat.mro.app.model.terms.common.services.UomService;
import uz.uat.mro.app.views.MainLayout;

@PageTitle(value = "Виды Ед. измерения")
@Route(value = "common/uom-types", layout = MainLayout.class)
public class UomTypesView extends VerticalLayout {
    private UomService service;
    private GridCrud<UomType> crud;
    private GridListDataView<UomType> dataView;
    private TextField searchField;
    private Button searchButton;

    /**
     * 
     */
    public UomTypesView(UomService service) {
        this.service = service;
        grid();
        add(crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(UomType.class);
        this.crud.getGrid().setColumns("id", "name", "description");

        this.crud.getGrid().getColumnByKey("id").setHeader("Код");
        this.crud.getGrid().getColumnByKey("name").setHeader("Наименование");
        this.crud.getGrid().getColumnByKey("description").setHeader("Описание");

        crud.setAddOperation(service::saveUomType);
        crud.setUpdateOperation(service::saveUomType);
        crud.setDeleteOperation(service::deleteUomType);
        crud.setFindAllOperation(service::findAllUomTypes);

        this.dataView = crud.getGrid().getListDataView();
        this.searchField = new TextField("", "поиск");
        this.searchButton = new Button(VaadinIcon.SEARCH.create());
        crud.getCrudLayout().addFilterComponents(searchField, searchButton);

        searchButton.addClickListener(click -> {
            dataView.removeFilters();
            dataView.setFilter(uomType -> uomType.getId().toLowerCase().contains(searchField.getValue().toLowerCase()));
        });

        searchField.addValueChangeListener(event -> {
            if (searchField.getValue().isBlank()) {
                dataView.removeFilters();
            }
        });

        CrudFormFactory<UomType> factory = crud.getCrudFormFactory();
        factory.setVisibleProperties("id", "name", "description");
        factory.setFieldCaptions("ИД", "Наименование", "Описание");
    }
}
