package uz.uat.mro.app.views;

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

import uz.uat.mro.app.views.about.AboutView;
import uz.uat.mro.app.views.common.CommonsView;
import uz.uat.mro.app.views.common.CountriesView;
import uz.uat.mro.app.views.common.CurrenciesView;
import uz.uat.mro.app.views.common.MaintenancesView;
import uz.uat.mro.app.views.common.OrganizationUnitTypesView;
import uz.uat.mro.app.views.common.StationsView;
import uz.uat.mro.app.views.common.UomTypesView;
import uz.uat.mro.app.views.common.UomsView;
import uz.uat.mro.app.views.common.WorkDaysView;
import uz.uat.mro.app.views.organization.OrganizationsView2;
import uz.uat.mro.app.views.start.StartView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
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
        H1 appName = new H1("UAT MRO Меню");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        SideNavItem start = new SideNavItem("Start", StartView.class, LineAwesomeIcon.LIST_SOLID.create());
        SideNavItem about = new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create());
        SideNavItem terms = new SideNavItem("Общие данные", CommonsView.class, VaadinIcon.OFFICE.create());
        terms.setPrefixComponent(VaadinIcon.BOOK.create());
        SideNavItem countries = new SideNavItem("Страны", CountriesView.class,
                LineAwesomeIcon.GLOBE_ASIA_SOLID.create());
        SideNavItem stations = new SideNavItem("Станции", StationsView.class, LineAwesomeIcon.MAP.create());
        SideNavItem currencies = new SideNavItem("Валюты", CurrenciesView.class,
                LineAwesomeIcon.MONEY_BILL_ALT.create());
        SideNavItem maintenance = new SideNavItem("Виды работ", MaintenancesView.class,
                LineAwesomeIcon.MONEY_BILL_ALT.create());
        SideNavItem unitTypes = new SideNavItem("Типы подразделений", OrganizationUnitTypesView.class,
                VaadinIcon.AIRPLANE.create());
        SideNavItem uomTypes = new SideNavItem("Виды Ед. измерения", UomTypesView.class,
                LineAwesomeIcon.FEMALE_SOLID.create());
        SideNavItem uoms = new SideNavItem("Ед. измерения", UomsView.class, LineAwesomeIcon.FEMALE_SOLID.create());
        SideNavItem calendar = new SideNavItem("Календарь", WorkDaysView.class, LineAwesomeIcon.FEMALE_SOLID.create());

        terms.addItem(countries, stations, currencies, maintenance, unitTypes, uomTypes, uoms, calendar);
        SideNavItem org = new SideNavItem("Организации", OrganizationsView2.class, VaadinIcon.OFFICE.create());
        nav.addItem(start, about, terms, org);
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
