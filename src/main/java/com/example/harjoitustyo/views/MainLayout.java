package com.example.harjoitustyo.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        // Ylätunniste (Header)
        H1 title = new H1("Harjoitustyo");
        title.getStyle().set("margin", "0").set("fontSize", "1.5rem");

        // Navigointi-yläpalkki
        addToNavbar(new DrawerToggle(), title);

        // Navigointi-valikko (Drawer)
        RouterLink etusivuLink = new RouterLink("Etusivu", EtusivuView.class);
        etusivuLink.addClassName("fancy-nav");

        RouterLink henkiloLink = new RouterLink("Henkilöt", HenkiloView.class);
        henkiloLink.addClassName("fancy-nav");

        RouterLink mittausLink = new RouterLink("Mittaukset", MittausView.class);
        mittausLink.addClassName("fancy-nav");

        addToDrawer(etusivuLink);
        addToDrawer(henkiloLink);
        addToDrawer(mittausLink);
    }
}
