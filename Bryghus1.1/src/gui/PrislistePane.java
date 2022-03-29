package gui;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Vare;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class PrislistePane extends GridPane {
    private ListView<Prisliste> lvwPrislister = new ListView<>();
    private ListView<Vare> lvwVarer = new ListView<>();

    private Button btnAddPrisliste, btnDeletePrisliste, btnEditPrisliste, btnTilføjVare, btntilføjVareGruppe, btnFjernVare;
    private Controller controller;

    public PrislistePane() {
        this.setPadding(new Insets(20));
        setHgap(40);
        setVgap(10);
        setGridLinesVisible(false);
        setMaxWidth(1000);

        controller = Controller.getController();

        Label lblVareGruppe = new Label("Prislister");
        this.add(lblVareGruppe, 0, 0);


        Label lblVarer = new Label("Varer");
        this.add(lblVarer, 1, 0);

        this.add(lvwPrislister, 0, 1, 1, 2);
        lvwPrislister.getItems().setAll(controller.getPrislister());

        this.add(lvwVarer, 1, 1, 1, 2);


        btnAddPrisliste = new Button("Tilføj Prisliste");
        btnAddPrisliste.setPrefWidth(120);
        btnAddPrisliste.setOnAction(event -> tilføjAction());

        btnDeletePrisliste = new Button("Slet Prisliste");
        btnDeletePrisliste.setPrefWidth(120);
        btnDeletePrisliste.setOnAction(event -> sletAction());

        btnEditPrisliste = new Button("Ret Prisliste");
        btnEditPrisliste.setPrefWidth(120);
        btnEditPrisliste.setOnAction(event -> retAction());

        VBox upperVbox = new VBox();
        upperVbox.getChildren().add(btnAddPrisliste);
        upperVbox.getChildren().add(btnDeletePrisliste);
        upperVbox.getChildren().add(btnEditPrisliste);
        upperVbox.setSpacing(10);
        this.add(upperVbox, 2, 1);

        btnTilføjVare = new Button("Tilføj Vare");
        btnTilføjVare.setPrefWidth(120);

        btntilføjVareGruppe = new Button("Slet Vare");
        btntilføjVareGruppe.setPrefWidth(120);

        btnFjernVare = new Button("Fjern Vare");
        btnFjernVare.setPrefWidth(120);

        VBox lowerVbox = new VBox();
        lowerVbox.getChildren().add(btntilføjVareGruppe);
        lowerVbox.getChildren().add(btnTilføjVare);
        lowerVbox.getChildren().add(btnFjernVare);
        lowerVbox.setSpacing(10);
        this.add(lowerVbox, 2, 2);
        lowerVbox.setAlignment(Pos.BOTTOM_RIGHT);
    }

    private void tilføjAction() {
        PrisListeWindow tilføj = new PrisListeWindow("Tilføj Prisliste");
        tilføj.showAndWait();

        lvwPrislister.getItems().setAll(controller.getPrislister());
    }

    private void sletAction() {
        Prisliste selected = lvwPrislister.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet Prisliste?");
            alert.setHeaderText("Vil du slette prislisten?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                controller.deletePrisliste(selected);
                lvwPrislister.getItems().setAll(controller.getPrislister());
            }
        }
    }

    private void retAction() {
        Prisliste selected = lvwPrislister.getSelectionModel().getSelectedItem();
        if (selected != null) {
            PrisListeWindow ret = new PrisListeWindow("Ret Prisliste", selected);
            ret.showAndWait();

            lvwPrislister.getItems().setAll(controller.getPrislister());
        }
    }


}
