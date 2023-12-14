package uz.uat.mro.app.views.common;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.terms.common.Currency;
import uz.uat.mro.app.model.terms.common.services.CurrencyService;
import uz.uat.mro.app.views.MainLayout;

@PageTitle(value = "Валюты")
@Route(value = "common/currencies", layout = MainLayout.class)
public class CurrenciesView extends VerticalLayout {
    private CurrencyService service;
    private GridCrud<Currency> grid;

    /**
     * 
     */
    public CurrenciesView(CurrencyService service) {
        this.service = service;
        grid();
        // add(new H3("Валюты"), grid);
        add(grid);
    }

    private void grid() {
        this.grid = new GridCrud<>(Currency.class);
        this.grid.getGrid().setColumns("id", "numeric", "name");
        this.grid.getGrid().getColumnByKey("id").setHeader("Код");
        this.grid.getGrid().getColumnByKey("numeric").setHeader("Цифр. код");
        this.grid.getGrid().getColumnByKey("name").setHeader("Наименование");

        CrudFormFactory<Currency> factory = grid.getCrudFormFactory();
        factory.setVisibleProperties("id", "numeric", "name");
        factory.setFieldCaptions("Код", "Цифр. код", "Наименование");

        grid.setAddOperation(service::save);
        grid.setUpdateOperation(service::save);
        grid.setDeleteOperation(service::delete);
        grid.setFindAllOperation(() -> service.findAll());
    }
}
