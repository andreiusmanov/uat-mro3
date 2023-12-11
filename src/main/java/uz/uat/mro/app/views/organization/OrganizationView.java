package uz.uat.mro.app.views.organization;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import uz.uat.mro.app.model.documents.organization.OrganizationService;
import uz.uat.mro.app.model.documents.organization.OrganizationUnit;
import uz.uat.mro.app.utils.Keys;
import uz.uat.mro.app.utils.MyUtils;
import uz.uat.mro.app.views.organization.forms.OrganizationForm;

@PageTitle("Организация")
@Route(value = "structure/organization", layout = OrganizationLayout.class)
public class OrganizationView extends VerticalLayout {
    private OrganizationService service;
    public OrganizationUnit organization;
    private OrganizationForm form;
    private Binder<OrganizationUnit> binder;
    private Button saveButton;
    private Button cancelButton;

    private MenuBar menu;

    public OrganizationView(OrganizationService service) {
        super();
        this.service = service;
        this.organization = (OrganizationUnit) MyUtils.getAttribute(Keys.ORGANIZATION);
        form();
        data();
        menu();
        buttons();
        disableEditing(true);
        add(menu, form, new HorizontalLayout(saveButton, cancelButton));
    }

    private void menu() {
        this.menu = new MenuBar();
        menu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY);
        MenuItem editItem = menu.addItem("Редактировать");
        editItem.addClickListener(click -> {
            Notification.show("clicked");
            disableEditing(true);
        });
    }

    private void form() {
        this.form = new OrganizationForm(true, service);
    }

    private void buttons() {
        this.saveButton = new Button("Сохранить");
        saveButton.addClickListener(click -> {
            save();
            disableEditing(true);
        });
        this.cancelButton = new Button("Отменить");
        cancelButton.addClickListener(click -> {
            cancel();
            disableEditing(true);
        });
    }

    private void save() {
        try {
            service.save(binder.getBean());
            Notification.show("Запись " + organization.getName() + " сохранена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    private void cancel() {
        try {
            binder.setBean(service.getOrganizationUnitById(organization.getArangoId()));
            Notification.show("Запись " + organization.getName() + " отменена", 5000, Position.MIDDLE);
        } catch (Exception e) {
            Notification.show(e.getMessage());
        }
    }

    private void data() {
        this.binder = new Binder<>(OrganizationUnit.class);
        binder.setBean(organization);
        binder.bindInstanceFields(form);
    }

    private void disableEditing(boolean enabled) {
        this.form.setReadOnly(enabled);
        this.saveButton.setVisible(!enabled);
        this.cancelButton.setVisible(!enabled);
        this.menu.setEnabled(enabled);
    }

}
