package com.example.harjoitustyo.views;

import com.example.harjoitustyo.model.Mittaus;
import com.example.harjoitustyo.repository.MittausRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "mittaukset", layout = MainLayout.class)
@PageTitle("Mittaukset")
public class MittausView extends VerticalLayout {

    private final MittausRepository repo;
    private final Grid<Mittaus> grid = new Grid<>(Mittaus.class);

    @Autowired
    public MittausView(MittausRepository repo) {
        this.repo = repo;
        grid.setColumns("id", "tyyppi", "arvo", "pvm", "henkilo.nimi");
        add(new Button("Uudelleenlataa", e -> grid.setItems(repo.findAll())), grid);
        grid.setItems(repo.findAll());
    }
}
