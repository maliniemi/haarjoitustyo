package com.example.harjoitustyo.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "user", layout = MainLayout.class)
@PageTitle("Käyttäjäsivu")
@RolesAllowed({"USER", "ADMIN"})
public class UserView extends VerticalLayout {
    public UserView() {
        add(new H2("Tervetuloa käyttäjäsivulle!"));
    }
}
