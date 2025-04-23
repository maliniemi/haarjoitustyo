package com.example.harjoitustyo.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.security.PermitAll;

@CssImport("./styles/shared-styles.css")
@PermitAll
public class MainLayout extends AppLayout {

    public MainLayout(@Autowired AuthenticationContext authContext,
                      @Autowired AccessAnnotationChecker accessChecker) {

        H1 title = new H1("Harjoitustyo");
        title.getStyle().set("margin", "0").set("fontSize", "1.5rem");

        Button logoutButton = new Button("Kirjaudu ulos", e -> {
            authContext.logout();
        });


        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.add(title, logoutButton);

        addToNavbar(new DrawerToggle(), header);

        RouterLink etusivuLink = new RouterLink("Etusivu", EtusivuView.class);
        etusivuLink.addClassName("fancy-nav");

        RouterLink henkiloLink = new RouterLink("Henkilöt", HenkiloView.class);
        henkiloLink.addClassName("fancy-nav");

        RouterLink mittausLink = new RouterLink("Mittaukset", MittausView.class);
        mittausLink.addClassName("fancy-nav");

        addToDrawer(etusivuLink, henkiloLink, mittausLink);

        if (accessChecker.hasAccess(UserView.class)) {
            RouterLink userLink = new RouterLink("Käyttäjäsivu", UserView.class);
            addToDrawer(userLink);
            userLink.addClassName("fancy-nav");
        }

        if (accessChecker.hasAccess(AdminView.class)) {
            RouterLink adminLink = new RouterLink("Ylläpitosivu", AdminView.class);
            addToDrawer(adminLink);
            adminLink.addClassName("fancy-nav");
        }
    }
}
