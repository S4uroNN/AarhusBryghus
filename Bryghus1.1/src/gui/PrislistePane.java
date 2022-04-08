package gui;

import application.controller.VareController;
import application.model.Prisliste;
import application.model.Vare;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import java.util.Optional;

public class PrislistePane extends GridPane {
    private ListView<Prisliste> lvwPrislister = new ListView<>();
    private ListView<Vare> lvwVarer = new ListView<>();

    private Button btnAddPrisliste, btnDeletePrisliste, btnEditPrisliste, btnTilføjVare, btnTilføjVareGruppe, btnFjernVare;
    private VareController vareController = VareController.getController();

    public PrislistePane() {
        this.setPadding(new Insets(20));
        setHgap(20);
        setVgap(10);
        setGridLinesVisible(false);
        setMaxWidth(700);

        vareController = VareController.getController();
        lvwPrislister.getItems().setAll(vareController.getPrislister());

        Label lblVareGruppe = new Label("Prislister");
        this.add(lblVareGruppe, 0, 0);


        Label lblVarer = new Label("Varer");
        this.add(lblVarer, 1, 0);

        this.add(lvwPrislister, 0, 1, 1, 2);
        lvwPrislister.getItems().setAll(vareController.getPrislister());

        ChangeListener<Prisliste> prislisteListener = (ov, oldPrisliste, newPrisliste) -> this.selectedPrislisteChanged();
        lvwPrislister.getSelectionModel().selectedItemProperty().addListener(prislisteListener);

        this.add(lvwVarer, 1, 1, 1, 2);

        btnAddPrisliste = new Button("Tilføj Prisliste");
        btnAddPrisliste.setPrefWidth(130);
        btnAddPrisliste.setOnAction(event -> tilføjAction());

        btnDeletePrisliste = new Button("Slet Prisliste");
        btnDeletePrisliste.setPrefWidth(130);
        btnDeletePrisliste.setOnAction(event -> sletAction());

        btnEditPrisliste = new Button("Ret Prisliste");
        btnEditPrisliste.setPrefWidth(130);
        btnEditPrisliste.setOnAction(event -> retAction());

        VBox upperVbox = new VBox();
        upperVbox.getChildren().add(btnAddPrisliste);
        upperVbox.getChildren().add(btnDeletePrisliste);
        upperVbox.getChildren().add(btnEditPrisliste);
        upperVbox.setSpacing(10);
        this.add(upperVbox, 2, 1);

        btnTilføjVare = new Button("Tilføj Vare");
        btnTilføjVare.setPrefWidth(130);
        btnTilføjVare.setOnAction(event -> tilføjVareAction());

        btnTilføjVareGruppe = new Button("Tilføj Varegruppe");
        btnTilføjVareGruppe.setPrefWidth(130);
        btnTilføjVareGruppe.setOnAction(event -> tilføjVaregruppeAction());

        btnFjernVare = new Button("Fjern Vare");
        btnFjernVare.setPrefWidth(130);
        btnFjernVare.setOnAction(event -> fjernVareAction());

        VBox lowerVbox = new VBox();
        lowerVbox.getChildren().add(btnTilføjVare);
        lowerVbox.getChildren().add(btnTilføjVareGruppe);
        lowerVbox.getChildren().add(btnFjernVare);
        lowerVbox.setSpacing(10);
        this.add(lowerVbox, 2, 2);
        lowerVbox.setAlignment(Pos.BOTTOM_RIGHT);
    }

    private void tilføjAction() {
        PrisListeWindow tilføj = new PrisListeWindow("Tilføj Prisliste");
        tilføj.showAndWait();

        lvwPrislister.getItems().setAll(vareController.getPrislister());

    }

    private void sletAction() {
        Prisliste selected = lvwPrislister.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet Prisliste?");
            alert.setHeaderText("Vil du slette prislisten?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                vareController.deletePrisliste(selected);
                lvwPrislister.getItems().setAll(vareController.getPrislister());
                lvwVarer.getItems().clear();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en prisliste!");
            alert.showAndWait();
        }
    }

    private void retAction() {
        Prisliste selected = lvwPrislister.getSelectionModel().getSelectedItem();
        if (selected != null) {
            PrisListeWindow ret = new PrisListeWindow("Ret Prisliste", selected);
            ret.showAndWait();

            lvwPrislister.getItems().setAll(vareController.getPrislister());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en prisliste!");
            alert.showAndWait();
        }
    }

    private void tilføjVareAction() {
        Prisliste selected = lvwPrislister.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TilføjVareTilPrislisteWindow tilføj = new TilføjVareTilPrislisteWindow("Tilføj til prisliste", selected);
            tilføj.showAndWait();

            lvwVarer.getItems().setAll(selected.getTilføjedeVarer());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en prisliste!");
            alert.showAndWait();
        }
    }

    private void tilføjVaregruppeAction() {
        Prisliste selected = lvwPrislister.getSelectionModel().getSelectedItem();
        if (selected != null) {
            TilføjVaregruppeTilPrislisteWindow tilføj = new TilføjVaregruppeTilPrislisteWindow("Tilføj til prisliste", selected);
            tilføj.showAndWait();

            lvwVarer.getItems().setAll(selected.getTilføjedeVarer());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en prisliste!");
            alert.showAndWait();
        }
    }

    private void fjernVareAction() {
        Prisliste selectedPrisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        Vare selectedVare = lvwVarer.getSelectionModel().getSelectedItem();
        if (selectedPrisliste != null && selectedVare != null) {
            vareController.fjernVarefromPrisliste(selectedPrisliste, selectedVare);
            lvwVarer.getItems().setAll(selectedPrisliste.getTilføjedeVarer());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare eller prisliste!");
            alert.showAndWait();
        }
    }

    private void selectedPrislisteChanged() {
        Prisliste selected = lvwPrislister.getSelectionModel().getSelectedItem();
        if (selected != null) {
            lvwVarer.getItems().setAll(selected.getTilføjedeVarer());
        }

    }


}
