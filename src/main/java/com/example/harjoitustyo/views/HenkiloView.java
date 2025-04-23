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
import jakarta.annotation.security.PermitAll;

@CssImport("./styles/custom.css")
@Route(value = "henkilot", layout = MainLayout.class)
@PageTitle("Henkilöt")
@PermitAll
public class HenkiloView extends VerticalLayout {

    private final HenkiloRepository repo;
    private final Grid<Henkilo> grid = new Grid<>(Henkilo.class);

    @Autowired
    public HenkiloView(HenkiloRepository repo) {
        this.repo = repo;

        TextField nimiFilter = new TextField();
        nimiFilter.setPlaceholder("Suodata nimellä");
        nimiFilter.setClearButtonVisible(true);
        nimiFilter.getStyle().set("marginRight", "10px"); // komponenttikohtainen tyyli
        nimiFilter.getStyle().set("width", "200px");

        TextField osoiteFilter = new TextField();
        osoiteFilter.setPlaceholder("Suodata osoitteella");
        osoiteFilter.setClearButtonVisible(true);
        osoiteFilter.getStyle().set("width", "200px");

        nimiFilter.addValueChangeListener(e -> updateGrid(nimiFilter.getValue(), osoiteFilter.getValue()));
        osoiteFilter.addValueChangeListener(e -> updateGrid(nimiFilter.getValue(), osoiteFilter.getValue()));

        add(nimiFilter, osoiteFilter);

        grid.setColumns("id", "nimi", "osoite");
        grid.getStyle().set("border", "1px solid #ccc");

        Button reload = new Button("Uudelleenlataa", e -> updateGrid(nimiFilter.getValue(), osoiteFilter.getValue()));
        add(reload, grid);

        updateGrid("", "");

    }

    private void updateGrid(String nimi, String osoite) {
        grid.setItems(repo.findByNimiContainingIgnoreCaseAndOsoiteContainingIgnoreCase(nimi, osoite));
    }
}
