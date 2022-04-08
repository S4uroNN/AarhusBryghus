package gui;

import application.controller.VareController;
import application.model.Prisliste;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrisListeWindow extends Stage {
    private Prisliste prisliste;

    public PrisListeWindow(String title, Prisliste prisliste) {
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

    public PrisListeWindow(String title) {
        this(title, null);
    }

    private TextField txfNavn;
    private VareController vareController;
    private Label lblError;

    private void initContent(GridPane pane) {
        vareController = VareController.getController();
        vareController.loadStorage();

        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        pane.setPrefWidth(250);
        pane.setPrefHeight(50);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 1, 0);

        Button btnOpret = new Button("Ok");
        pane.add(btnOpret, 1, 1);
        GridPane.setHalignment(btnOpret, HPos.RIGHT);
        btnOpret.setOnAction(event -> this.okAction());

        lblError = new Label();
        pane.add(lblError, 0, 2,2,1);
        lblError.setStyle("-fx-text-fill: red");
    }

    public void stop() {
        vareController.saveStorage();
    }

    private void initControls() {
        if (prisliste != null) {
            txfNavn.setText(prisliste.getNavn());
        } else {
            txfNavn.clear();
        }
    }


    private void okAction() {
        String name = txfNavn.getText().trim();
        if (name.length() == 0) {
            lblError.setText("Navn er tomt");
        } else {
            if (prisliste != null) {
                vareController.updatePrisliste(prisliste, name);
            } else {
                vareController.createPrisliste(txfNavn.getText());
            }
            this.hide();
        }
    }
}
