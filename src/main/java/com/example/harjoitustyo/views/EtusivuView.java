package com.example.harjoitustyo.views;

import com.example.harjoitustyo.views.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Etusivu")
public class EtusivuView extends VerticalLayout {

    public EtusivuView() {
        setSizeFull(); // ottaa kaiken korkeuden
        setPadding(false);
        setSpacing(false);

        // Välialue
        VerticalLayout content = new VerticalLayout();
        content.setWidthFull();
        content.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        content.add(new H1("Tervetuloa harjoitustyöhön!"));
        content.add(new Paragraph("Valitse navigaatiosta toiminto."));
        content.setSizeFull(); // Täyttää tilan niin että footer menee alas

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