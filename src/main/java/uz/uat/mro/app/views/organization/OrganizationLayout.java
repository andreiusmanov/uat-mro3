package uz.uat.mro.app.views.organization;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import uz.uat.mro.app.views.employee.EmployeesView;

public class OrganizationLayout extends AppLayout {
    private H2 viewTitle;

    public OrganizationLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("UAT MRO");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);
        SideNav altNavigation = createAltNavigation();
        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, altNavigation, scroller, createFooter());
    }

    private SideNav createAltNavigation() {
        SideNav nav = new SideNav();
        SideNavItem home = new SideNavItem("Организации", OrganizationsView.class, VaadinIcon.HOME_O.create());
        nav.addItem(home);
        return nav;
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        // SideNavItem menu = new SideNavItem("Организации меню",
        // OrganizationsView.class, LineAwesomeIcon.HOME_SOLID.create());
        SideNavItem org = new SideNavItem("Организация", OrganizationView.class, LineAwesomeIcon.BUILDING.create());
        SideNavItem structure = new SideNavItem("Орг. Структуры", OrganizationStructuresView.class,
                VaadinIcon.STAR.create());
        SideNavItem employee = new SideNavItem("Сотрудники", EmployeesView.class, LineAwesomeIcon.USERS_SOLID.create());
        nav.addItem(org, structure, employee);
        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

}
