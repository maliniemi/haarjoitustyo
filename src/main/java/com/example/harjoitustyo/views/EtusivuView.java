package com.example.harjoitustyo.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;

import java.util.Locale;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Etusivu")
@PermitAll
public class EtusivuView extends VerticalLayout {

    public EtusivuView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Kielivalitsin
        ComboBox<Locale> languageSelector = new ComboBox<>();
        languageSelector.setLabel("Valitse kieli / Select language");
        languageSelector.setItems(Locale.ENGLISH, new Locale("fi"));
        languageSelector.setItemLabelGenerator(locale -> locale.getDisplayLanguage(locale));

        // Haetaan nykyinen kieli sessiosta, oletuksena suomi
        Locale currentLocale = VaadinSession.getCurrent().getAttribute(Locale.class);
        if (currentLocale == null) {
            currentLocale = new Locale("fi"); // Oletuksena suomi
            VaadinSession.getCurrent().setAttribute(Locale.class, currentLocale);
        }
        languageSelector.setValue(currentLocale);

        languageSelector.addValueChangeListener(event -> {
            VaadinSession.getCurrent().setAttribute(Locale.class, event.getValue());
            UI.getCurrent().getPage().reload(); // Päivitä näkymä uudella kielellä
        });

        // Yläpalkki kielivalitsimelle
        HorizontalLayout topBar = new HorizontalLayout(languageSelector);
        topBar.setWidthFull();
        topBar.setPadding(true);
        topBar.setJustifyContentMode(JustifyContentMode.END);
        add(topBar);

        // Haetaan nykyinen kieli sessiosta
        Locale currentSessionLocale = VaadinSession.getCurrent().getAttribute(Locale.class);

        // Päivitetään selaimen sivun otsikko lokalisoidusti
        UI.getCurrent().getPage().setTitle(getTranslation("page.title.home", currentSessionLocale));

        // Keskialue
        VerticalLayout content = new VerticalLayout();
        content.setWidthFull();
        content.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        content.add(new H1(getTranslation("greeting", currentSessionLocale)));
        content.add(new Paragraph(getTranslation("instruction", currentSessionLocale)));
        content.setSizeFull();

        // Footer
        HorizontalLayout footer = new HorizontalLayout(new Paragraph("© 2025 Harjoitustyo Oy"));
        footer.setWidthFull();
        footer.getStyle()
                .set("padding", "10px")
                .set("backgroundColor", "#f0f0f0")
                .set("borderTop", "1px solid #ccc")
                .set("justifyContent", "center");

        add(content, footer);
        expand(content);
    }
}
