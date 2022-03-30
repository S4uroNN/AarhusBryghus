package gui;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TilføjVaregruppeTilPrislisteWindow extends Stage {
    private Prisliste prisliste;

    public TilføjVaregruppeTilPrislisteWindow(String title, Prisliste prisliste) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.prisliste = prisliste;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private Controller controller;
    private TextField txfPris;
    private ListView<VareGruppe> lvwAlleVareGrupper = new ListView<>();
    private ListView<Vare> lvwTilføjedeVarer = new ListView<>();

    private void initContent(GridPane pane) {
        controller = Controller.getController();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        pane.setPrefWidth(600);
        pane.setPrefHeight(500);

        Label lblAlleVarer = new Label("Alle VareGrupper");
        pane.add(lblAlleVarer, 0, 0);

        Label lblTilføjedeVarer = new Label("Tilføjede Varer");
        pane.add(lblTilføjedeVarer, 1, 0);

        Label lblPris = new Label("Pris");
        pane.add(lblPris, 2, 0);

        pane.add(lvwAlleVareGrupper, 0, 1, 1, 2);
        lvwAlleVareGrupper.getItems().setAll(controller.getVareGrupper());

        pane.add(lvwTilføjedeVarer, 1, 1, 1, 2);
        lvwTilføjedeVarer.getItems().setAll(prisliste.getTilføjedeVarer());

        txfPris = new TextField();
        pane.add(txfPris, 2, 1);

        Button btnAdd = new Button("Tilføj");
        btnAdd.setOnAction(event -> addAction());

        Button btnRemove = new Button("Fjern");
        btnRemove.setOnAction(event -> removeAction());

        Button btnEdit = new Button("Rediger");
        btnEdit.setOnAction(event -> editAction());

        VBox vbox = new VBox();
        vbox.getChildren().add(btnAdd);
        vbox.getChildren().add(btnRemove);
        vbox.getChildren().add(btnEdit);
        vbox.setSpacing(10);
        pane.add(vbox, 2, 2);

        Button btnOk = new Button("Ok");
        pane.add(btnOk, 2, 3);
        btnOk.setOnAction(event -> okAction());

    }

    private void addAction() {
        VareGruppe selected = lvwAlleVareGrupper.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.addVareGruppeToPrisliste(prisliste, selected, Integer.parseInt(txfPris.getText()));
        }
        lvwTilføjedeVarer.getItems().setAll(prisliste.getTilføjedeVarer());
    }

    private void removeAction() {
        Vare selected = lvwTilføjedeVarer.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.fjernVarefromPrisliste(prisliste, selected);
        }
    }

    private void editAction() {
        Vare selected = lvwTilføjedeVarer.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.updatePris(prisliste, selected, Integer.parseInt(txfPris.getText()));
        }
    }

    private void okAction() {
        this.hide();
    }


}
