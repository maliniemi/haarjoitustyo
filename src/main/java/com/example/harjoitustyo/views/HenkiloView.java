package com.example.harjoitustyo.views;

import com.example.harjoitustyo.model.Henkilo;
import com.example.harjoitustyo.repository.HenkiloRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@CssImport("./styles/custom.css")
@Route(value = "henkilot", layout = MainLayout.class)
@PageTitle("Henkilöt")
public class HenkiloView extends VerticalLayout {

    private final HenkiloRepository repo;
    private final Grid<Henkilo> grid = new Grid<>(Henkilo.class);

    @Autowired
    public HenkiloView(HenkiloRepository repo) {
        this.repo = repo;

        // 1) Suodatin: nimi
        TextField nimiFilter = new TextField();
        nimiFilter.setPlaceholder("Suodata nimellä");
        nimiFilter.setClearButtonVisible(true);
        nimiFilter.getStyle().set("marginRight", "10px"); // komponenttikohtainen tyyli
        nimiFilter.getStyle().set("width", "200px");

        // 2) Suodatin: osoite
        TextField osoiteFilter = new TextField();
        osoiteFilter.setPlaceholder("Suodata osoitteella");
        osoiteFilter.setClearButtonVisible(true);
        osoiteFilter.getStyle().set("width", "200px");

        // 3) Listenerit – nyt molemmat muuttujat on jo määritelty
        nimiFilter.addValueChangeListener(e -> updateGrid(nimiFilter.getValue(), osoiteFilter.getValue()));
        osoiteFilter.addValueChangeListener(e -> updateGrid(nimiFilter.getValue(), osoiteFilter.getValue()));

        // 4) Lisää suodattimet ja Grid sivulle
        add(nimiFilter, osoiteFilter);

        // 5) Gridin sarakkeet
        grid.setColumns("id", "nimi", "osoite");
        grid.getStyle().set("border", "1px solid #ccc"); // komponenttikohtainen tyyli

        // 6) Päivityspainike
        Button reload = new Button("Uudelleenlataa", e -> updateGrid(nimiFilter.getValue(), osoiteFilter.getValue()));
        add(reload, grid);

        // 7) Lataa alkutiedot
        updateGrid("", "");

    }

    private void updateGrid(String nimi, String osoite) {
        grid.setItems(repo.findByNimiContainingIgnoreCaseAndOsoiteContainingIgnoreCase(nimi, osoite));
    }
}
