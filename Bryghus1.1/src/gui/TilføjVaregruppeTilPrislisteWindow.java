package gui;

import application.controller.VareController;
import application.model.Prisliste;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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


    private VareController vareController;
    private TextField txfPris, txfKlip;
    private ListView<VareGruppe> lvwAlleVareGrupper = new ListView<>();
    private ListView<Vare> lvwTilføjedeVarer = new ListView<>();
    private int buttonWidth = 130;

    private void initContent(GridPane pane) {
        vareController = VareController.getController();
        vareController.loadStorage();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        pane.setPrefWidth(600);
        pane.setPrefHeight(400);

        Label lblAlleVarer = new Label("Alle VareGrupper");
        pane.add(lblAlleVarer, 0, 0);

        Label lblTilføjedeVarer = new Label("Tilføjede Varer");
        pane.add(lblTilføjedeVarer, 1, 0);

        Label lblPris = new Label("Pris");
        pane.add(lblPris, 2, 0);

        Label lblKlip = new Label("Klip");
        pane.add(lblKlip,2,2);

        pane.add(lvwAlleVareGrupper, 0, 1, 1, 4);
        lvwAlleVareGrupper.getItems().setAll(vareController.getVareGrupper());

        pane.add(lvwTilføjedeVarer, 1, 1, 1, 4);
        lvwTilføjedeVarer.getItems().setAll(prisliste.getTilføjedeVarer());

        ChangeListener<Vare> vareListener = (ov, oldVare, newVare) -> this.selectedVareChanged();
        lvwTilføjedeVarer.getSelectionModel().selectedItemProperty().addListener(vareListener);

        txfPris = new TextField();
        pane.add(txfPris, 2, 1);

        txfKlip = new TextField();
        pane.add(txfKlip,2,3);

        Button btnAdd = new Button("Tilføj");
        btnAdd.setOnAction(event -> addAction());
        btnAdd.setPrefWidth(buttonWidth);

        Button btnRemove = new Button("Fjern");
        btnRemove.setOnAction(event -> removeAction());
        btnRemove.setPrefWidth(buttonWidth);

        Button btnEdit = new Button("Rediger");
        btnEdit.setOnAction(event -> editAction());
        btnEdit.setPrefWidth(buttonWidth);

        Button btnOk = new Button("Ok");
        btnOk.setOnAction(event -> okAction());
        btnOk.setPrefWidth(buttonWidth);

        VBox vbox = new VBox();
        vbox.getChildren().add(btnAdd);
        vbox.getChildren().add(btnRemove);
        vbox.getChildren().add(btnEdit);
        vbox.getChildren().add(btnOk);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        pane.add(vbox, 2, 4);



    }

    public void stop() {
        vareController.saveStorage();
    }

    private void addAction() {
        VareGruppe selected = lvwAlleVareGrupper.getSelectionModel().getSelectedItem();
        if (selected != null) {
            vareController.addVareGruppeToPrisliste(prisliste, selected, Double.parseDouble(txfPris.getText()), Integer.parseInt(txfKlip.getText()));
            lvwTilføjedeVarer.getItems().setAll(prisliste.getTilføjedeVarer());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en varegruppe!");
            alert.showAndWait();
        }

    }

    private void removeAction() {
        Vare selected = lvwTilføjedeVarer.getSelectionModel().getSelectedItem();
        if (selected != null) {
            vareController.fjernVarefromPrisliste(prisliste, selected);
            lvwTilføjedeVarer.getItems().setAll(prisliste.getTilføjedeVarer());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare!");
            alert.showAndWait();
        }
    }

    private void editAction() {
        Vare selected = lvwTilføjedeVarer.getSelectionModel().getSelectedItem();
        if (selected != null) {
            vareController.updatePris(prisliste, selected, Double.parseDouble(txfPris.getText()), Integer.parseInt(txfKlip.getText()));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare!");
            alert.showAndWait();
        }
    }

    private void okAction() {
        this.hide();
    }

    private void selectedVareChanged() {
        Vare selected = lvwTilføjedeVarer.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txfPris.setText(selected.getPrisUdenPant(prisliste) + "");
            txfKlip.setText(selected.getKlipPris(prisliste) + "");
        }
    }

}
