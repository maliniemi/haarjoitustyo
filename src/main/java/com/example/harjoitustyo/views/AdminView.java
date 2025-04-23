package com.example.harjoitustyo.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "admin", layout = MainLayout.class)
@PageTitle("Ylläpito­sivu")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {
    public AdminView() {
        add(new H2("Tervetuloa ylläpitäjälle!"));
    }
}
