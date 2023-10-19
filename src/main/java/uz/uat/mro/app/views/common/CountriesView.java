package uz.uat.mro.app.views.common;

import java.util.List;

import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.terms.common.Country;
import uz.uat.mro.app.model.terms.common.services.CountriesService;

@PageTitle(value = "Страны")
@Route(value = "common/countries", layout = CommonLayout.class)
public class CountriesView extends VerticalLayout {
    private CountriesService service;
    private GridCrud<Country> crud;

    
    public CountriesView(CountriesService service) {
        this.service = service;
        grid();
        add(new H3("Страны"), crud);
    }

    private void grid() {
        this.crud = new GridCrud<>(Country.class);
        crud.getGrid().setColumns("shortName", "id", "code3", "numeric", "name");
        crud.getGrid().getColumnByKey("shortName").setHeader("Кратк. название");
        crud.getGrid().getColumnByKey("id").setHeader("Код2");
        crud.getGrid().getColumnByKey("code3").setHeader("Код3");
        crud.getGrid().getColumnByKey("numeric").setHeader("Цифр. код");
        crud.getGrid().getColumnByKey("name").setHeader("Наименование");

        CrudFormFactory<Country> factory = crud.getCrudFormFactory();
        factory.setVisibleProperties("shortName", "id", "code3", "numeric", "name");
        factory.setFieldCaptions("Кратк. название", "Код2", "Код3", "Цифр. код", "Наименование");

        crud.setAddOperation(service::save);
        crud.setUpdateOperation(service::save);
        crud.setDeleteOperation(service::delete);
        crud.setFindAllOperation(() -> service.findAll());
    }
}
