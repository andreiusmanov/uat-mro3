package uz.uat.mro.app.views.organization;

import java.util.List;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.views.MainLayout;

@PageTitle(value = "Список Организаций")
@Route(value = "uat/organizations", layout = MainLayout.class)
public class OrganizationsView extends VerticalLayout {
    private OrganizationService service;
    private Grid<OrganizationUnit> grid;
    private MenuBar menu;
    private MenuItem viewItem;
    private MenuItem editItem;
    private MenuItem addItem;
    private MenuItem deleteItem;
    private MenuItem unitsItem;
    private MenuItem facilitiesItem;
    private MenuItem specialItem;

    public OrganizationsView(OrganizationService service) {
        this.service = service;
        grid();
        menu();
        add(menu, grid);
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_PRIMARY);
        viewItem = menu.addItem("Просмотр", " Просмотреть данные об организации", click -> {
            Notification.show("Просмотр");
        });

        editItem = menu.addItem("Редактирование", " Редактировать данные об организации", click -> {
            Notification.show("Редатировать");
        });

        unitsItem = menu.addItem("Подразделения", " Просмотреть данные о подразделениях организации", click -> {
            Notification.show("Просмотр подразделений");
        });

        facilitiesItem = menu.addItem("Объекты", " Просмотреть данные об объектах организации", click -> {
            Notification.show("Просмотр объектов");
        });

        specialItem = menu.addItem("Спец. операции", "Спец. операции", click -> {
            Notification.show("Спец. операции");
        });
        addItem = specialItem.getSubMenu().addItem("Добавить");
        addItem.addClickListener(click -> {
            Notification.show("Добавить");
        });
        deleteItem = specialItem.getSubMenu().addItem("Удалить");
        deleteItem.addClickListener(click -> {
            Notification.show("Удалить");
        });
    }

    private void grid() {
        this.grid = new Grid<>(OrganizationUnit.class);
        List<OrganizationUnit> units = service.findMainOrganizations("Организация");
        this.grid.getSelectionModel().addSelectionListener(selected -> {
            boolean res = selected.getFirstSelectedItem().isPresent();
            viewItem.setEnabled(res);
            editItem.setEnabled(res);
            unitsItem.setEnabled(res);
            facilitiesItem.setEnabled(res);
            deleteItem.setEnabled(res);
        });
        this.grid.setItems(units);
        this.grid.setColumns("name", "code", "description");
    }

}
