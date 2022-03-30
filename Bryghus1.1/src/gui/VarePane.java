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

import java.util.Optional;


public class VarePane extends GridPane {
    private ListView<Vare> lbwvarer = new ListView<>();
    private ListView<VareGruppe> lbwvareGruppe = new ListView<>();

    private Button btnAddVare,btnAddVareGruppe, btnDeleteVare, btnDeleteVareGruppe, btnEditVareGruppe, btnEditVare;

    private Controller controller;

    public VarePane(){

        controller = Controller.getController();

        this.setPadding(new Insets(20));
        setHgap(40);
        setVgap(10);
        setGridLinesVisible(false);
        setMaxWidth(1000);

        Label lblVareGruppe = new Label("Vare Gruppe");
        this.add(lblVareGruppe,0,0);
        this.add(lbwvareGruppe,0,1,1,2);
        this.add(lbwvarer,1,1,1,2);

        Label lblVarer = new Label("Varer");
        this.add(lblVarer, 1,0);



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
        VBox vbox = new VBox();
        VBox vbox2 = new VBox();
        vbox.getChildren().add(btnAddVare);
        vbox.getChildren().add(btnDeleteVare);
        vbox.getChildren().add(btnEditVare);
        vbox2.getChildren().add(btnAddVareGruppe);
        vbox2.getChildren().add(btnDeleteVareGruppe);
        vbox2.getChildren().add(btnEditVareGruppe);
        vbox.setSpacing(10);
        vbox2.setSpacing(10);
        vbox2.setAlignment(Pos.BOTTOM_CENTER);
        this.add(vbox,2,1);
        this.add(vbox2,2,2);

        ChangeListener<VareGruppe> listener = (ov, oldArrangement, newArrangement) -> this.selectedVareGruppeChanged();
        lbwvareGruppe.getSelectionModel().selectedItemProperty().addListener(listener);

    }

    private void selectedVareGruppeChanged(){
        this.updateControls();
    }
    private void updateControls() {
        VareGruppe vareGruppe = lbwvareGruppe.getSelectionModel().getSelectedItem();

        if (vareGruppe != null) {
            lbwvarer.getItems().setAll(vareGruppe.getVarer());
        }
    }

    private void tilføjActionVare() {
        VareGruppe vareGruppe = lbwvareGruppe.getSelectionModel().getSelectedItem();
        TilføjVareWindow tilføjVareWindow = new TilføjVareWindow(vareGruppe);
        tilføjVareWindow.showAndWait();

        if(vareGruppe!=null){
            lbwvarer.getItems().setAll(vareGruppe.getVarer());
        }

    }
    private void retVareAction(){
        Vare vare = lbwvarer.getSelectionModel().getSelectedItem();
        TilføjVareWindow ret = new TilføjVareWindow(vare);
        ret.showAndWait();

        updateControls();
    }

    private void sletVareAction() {
        Vare vare = lbwvarer.getSelectionModel().getSelectedItem();
        VareGruppe vareGruppe = lbwvareGruppe.getSelectionModel().getSelectedItem();
        if (vare != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet Vare");
            alert.setHeaderText("Er du sikker?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                controller.removeVareFromGruppe(vare,vareGruppe);
                lbwvarer.getItems().setAll(vareGruppe.getVarer());
            }
        }
        updateControls();
    }

    private void tilføjActionVareGruppe() {
        TilføjVareGruppeWindow dia = new TilføjVareGruppeWindow("Tilføj VareGruppe");
        dia.showAndWait();

        lbwvareGruppe.getItems().setAll(controller.getVareGrupper());


    }
    private void sletActionVareGruppe() {
        VareGruppe vareGruppe = lbwvareGruppe.getSelectionModel().getSelectedItem();
        if (vareGruppe != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet Vare Gruppe");
            alert.setHeaderText("Er du sikker?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get()==ButtonType.OK)) {
                controller.deleteVareGruppe(vareGruppe);
                lbwvareGruppe.getItems().setAll(controller.getVareGrupper());
            }
        }
    }
    private void retActionVareGruppe(){
        VareGruppe vareGruppe = lbwvareGruppe.getSelectionModel().getSelectedItem();
        if (vareGruppe != null) {

            TilføjVareGruppeWindow dia = new TilføjVareGruppeWindow("Ret VareGruppe", vareGruppe);
            dia.showAndWait();
        }

        lbwvareGruppe.getItems().setAll(controller.getVareGrupper());
    }
}
