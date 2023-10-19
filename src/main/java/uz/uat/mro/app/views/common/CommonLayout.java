package uz.uat.mro.app.views.common;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

import uz.uat.mro.app.views.start.StartView;

public class CommonLayout extends AppLayout {
    private H2 viewTitle;

    public CommonLayout() {
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

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Start", StartView.class, LineAwesomeIcon.HOME_SOLID.create()));
        nav.addItem(new SideNavItem("Страны", CountriesView.class, LineAwesomeIcon.GLOBE_ASIA_SOLID.create()));
        nav.addItem(new SideNavItem("Станции", StationsView.class, LineAwesomeIcon.MAP.create()));
        nav.addItem(new SideNavItem("Валюты", CurrenciesView.class, LineAwesomeIcon.MONEY_BILL_ALT.create()));
        nav.addItem(new SideNavItem("Виды работ", MaintenancesView.class, LineAwesomeIcon.MONEY_BILL_ALT.create()));
        nav.addItem(new SideNavItem("Персоналии", PersonsView.class, LineAwesomeIcon.MALE_SOLID.create()));
        nav.addItem(new SideNavItem("Сотрудники", EmployeesView.class, LineAwesomeIcon.FEMALE_SOLID.create()));
        nav.addItem(new SideNavItem("Ед. измерения", UomsView.class, LineAwesomeIcon.FEMALE_SOLID.create()));
        nav.addItem(new SideNavItem("Календарь", WorkDaysView.class, LineAwesomeIcon.FEMALE_SOLID.create()));
        nav.addItem(new SideNavItem("OrganizationUnitNames", OrganizationUnitNamesView.class,
                VaadinIcon.AIRPLANE.create()));
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