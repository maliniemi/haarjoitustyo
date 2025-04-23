package com.example.harjoitustyo.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("access-denied")
@PageTitle("Pääsy evätty")

public class AccessDeniedView extends VerticalLayout {

    public AccessDeniedView() {
        add(
                new H2("🚫 Pääsy evätty: Sinulla ei ole oikeuksia tälle sivulle."),
                new RouterLink("Takaisin etusivulle", EtusivuView.class)
        );
    }
}
