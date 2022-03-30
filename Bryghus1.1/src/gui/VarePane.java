package gui;

import application.controller.Controller;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.security.URIParameter;
import java.util.Optional;


public class VarePane extends GridPane {
    private ListView<Vare> lvwAlleVare = new ListView<>();
    private ListView<VareGruppe> lvwvareGruppe = new ListView<>();
    private ListView<Vare> lvwVareInGruppe = new ListView<>();

    private Button btnAddVare,btnAddVareGruppe, btnDeleteVare, btnDeleteVareGruppe, btnEditVareGruppe, btnEditVare, btnTilføjTilVareGruppe;
    private Button btnFjernVare;

    private Controller controller;
    private Storage storage = Storage.getInstance();

    public VarePane(){
        

        this.setPadding(new Insets(20));
        setHgap(40);
        setVgap(10);
        setGridLinesVisible(false);
        setMaxWidth(1200);

        Label lblVareGruppe = new Label("Vare Gruppe");
        this.add(lblVareGruppe,0,0);
        this.add(lvwvareGruppe,0,1,1,2);
        this.add(lvwVareInGruppe,1,1,1,2);
        this.add(lvwAlleVare,2,1,1,2);

        Label lblVarer = new Label("Varer");
        this.add(lblVarer, 2,0);

        Label lblVarerInGruppe = new Label("Vare I gruppe");
        this.add(lblVarerInGruppe,1,0);



        btnAddVare = new Button("Tilføj Vare");
        btnAddVare.setPrefWidth(120);
        btnAddVare.setOnAction(event -> tilføjActionVare());
        btnAddVareGruppe = new Button("Tilføj Vare Gruppe");
        btnAddVareGruppe.setPrefWidth(120);
        btnAddVareGruppe.setOnAction(event -> tilføjActionVareGruppe());
        btnDeleteVare = new Button("Slet Vare");
        btnDeleteVare.setOnAction(event -> sletVareAction());
        btnDeleteVare.setPrefWidth(120);
        btnDeleteVareGruppe = new Button("Slet Vare Gruppe");
        btnDeleteVareGruppe.setPrefWidth(120);
        btnDeleteVareGruppe.setOnAction(event -> sletActionVareGruppe());
        btnEditVareGruppe = new Button("Ret VareGruppe");
        btnEditVareGruppe.setPrefWidth(120);
        btnEditVareGruppe.setOnAction(event -> retActionVareGruppe());
        btnEditVare = new Button("Ret Vare");
        btnEditVare.setOnAction(event -> retVareAction());
        btnEditVare.setPrefWidth(120);

        btnTilføjTilVareGruppe = new Button("Tilføj til Varegruppe");
        btnTilføjTilVareGruppe.setPrefWidth(120);
        btnTilføjTilVareGruppe.setOnAction(event -> tilføjAction());
        btnFjernVare = new Button("Fjern fra gruppe");
        btnFjernVare.setPrefWidth(120);
        btnFjernVare.setOnAction(event -> fjervareAction());
        VBox vbox = new VBox();
        VBox vbox2 = new VBox();
        vbox.getChildren().add(btnAddVare);
        vbox.getChildren().add(btnDeleteVare);
        vbox.getChildren().add(btnEditVare);
        vbox.getChildren().add(btnTilføjTilVareGruppe);
        vbox.getChildren().add(btnFjernVare);
        vbox2.getChildren().add(btnAddVareGruppe);
        vbox2.getChildren().add(btnDeleteVareGruppe);
        vbox2.getChildren().add(btnEditVareGruppe);
        vbox.setSpacing(10);
        vbox2.setSpacing(10);
        vbox2.setAlignment(Pos.BOTTOM_CENTER);
        this.add(vbox,3,1);
        this.add(vbox2,3,2);

        ChangeListener<VareGruppe> listener = (ov, oldArrangement, newArrangement) -> this.selectedVareGruppeChanged();
        lvwvareGruppe.getSelectionModel().selectedItemProperty().addListener(listener);

    }

    private void selectedVareGruppeChanged(){
        this.updateControls();
    }
    private void updateControls() {
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();

        if (vareGruppe != null) {
            lvwVareInGruppe.getItems().setAll(vareGruppe.getVarer());
        }
    }

    private void tilføjActionVare() {
        TilføjVareWindow tilføjVareWindow = new TilføjVareWindow();
        tilføjVareWindow.showAndWait();

        lvwAlleVare.getItems().setAll(storage.getVarer());

    }
    private void tilføjAction(){
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();
        Vare vare = lvwAlleVare.getSelectionModel().getSelectedItem();

        controller.addVareToVareGruppe(vare,vareGruppe);
        updateControls();
    }
    private void fjervareAction() {
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();
        Vare vare = lvwVareInGruppe.getSelectionModel().getSelectedItem();

        controller.removeVareFromGruppe(vare,vareGruppe);
        updateControls();

    }
    private void retVareAction(){
        Vare vare = lvwAlleVare.getSelectionModel().getSelectedItem();
        TilføjVareWindow ret = new TilføjVareWindow(vare);
        ret.showAndWait();

        updateControls();
    }

    private void sletVareAction() {
        Vare vare = lvwAlleVare.getSelectionModel().getSelectedItem();
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();
        if (vare != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet Vare");
            alert.setHeaderText("Er du sikker?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                controller.removeVareFromGruppe(vare,vareGruppe);
                lvwAlleVare.getItems().setAll(vareGruppe.getVarer());
            }
        }
        updateControls();
    }

    private void tilføjActionVareGruppe() {
        TilføjVareGruppeWindow dia = new TilføjVareGruppeWindow("Tilføj VareGruppe");
        dia.showAndWait();

        lvwvareGruppe.getItems().setAll(controller.getVareGrupper());


    }
    private void sletActionVareGruppe() {
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();
        if (vareGruppe != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet Vare Gruppe");
            alert.setHeaderText("Er du sikker?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get()==ButtonType.OK)) {
                controller.deleteVareGruppe(vareGruppe);
                lvwvareGruppe.getItems().setAll(controller.getVareGrupper());
            }
        }
    }
    private void retActionVareGruppe(){
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();
        if (vareGruppe != null) {

            TilføjVareGruppeWindow dia = new TilføjVareGruppeWindow("Ret VareGruppe", vareGruppe);
            dia.showAndWait();
        }

        lvwvareGruppe.getItems().setAll(controller.getVareGrupper());
    }
}
