package com.example.harjoitustyo.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Kirjautuminen")
@AnonymousAllowed
@CssImport("./styles/shared-styles.css")
public class LoginView extends VerticalLayout {

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 title = new H1("Harjoitustyo – kirjaudu sisään");

        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Header header = new LoginI18n.Header();
        header.setTitle("Käyttäjätunnus");
        header.setDescription("Syötä tunnukset");
        i18n.setHeader(header);


        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(i18n);
        loginForm.setAction("login");

        add(title, loginForm);
    }
}