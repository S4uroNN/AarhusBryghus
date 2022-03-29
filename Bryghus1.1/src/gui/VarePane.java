package gui;

import application.controller.Controller;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


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
        this.add(lbwvareGruppe,0,1);
        this.add(lbwvarer,1,1);

        Label lblVarer = new Label("Varer");
        this.add(lblVarer, 1,0);



        btnAddVare = new Button("Tilføj Vare");
        btnAddVare.setPrefWidth(120);
        btnAddVare.setOnAction(event -> tilføjActionVare());
        btnAddVareGruppe = new Button("Tilføj Vare Gruppe");
        btnAddVareGruppe.setPrefWidth(120);
        btnAddVareGruppe.setOnAction(event -> tilføjActionVareGruppe());
        btnDeleteVare = new Button("Slet Vare");
        btnDeleteVare.setPrefWidth(120);
        btnDeleteVareGruppe = new Button("Slet Vare Gruppe");
        btnDeleteVareGruppe.setPrefWidth(120);
        btnEditVareGruppe = new Button("Ret VareGruppe");
        btnEditVareGruppe.setPrefWidth(120);
        btnEditVare = new Button("Ret Vare");
        btnEditVare.setPrefWidth(120);
        VBox vbox = new VBox();
        vbox.getChildren().add(btnAddVare);
        vbox.getChildren().add(btnDeleteVare);
        vbox.getChildren().add(btnEditVare);
        vbox.getChildren().add(btnAddVareGruppe);
        vbox.getChildren().add(btnDeleteVareGruppe);
        vbox.getChildren().add(btnEditVareGruppe);
        vbox.setSpacing(10);
        this.add(vbox,2,1);

    }

    private void tilføjActionVare() {
        TilføjVareWindow tilføjVareWindow = new TilføjVareWindow(null);
        tilføjVareWindow.showAndWait();
    }

    private void tilføjActionVareGruppe() {
        TilføjVareGruppeWindow dia = new TilføjVareGruppeWindow("Tilføj VareGruppe");
        dia.showAndWait();

        lbwvareGruppe.getItems().setAll(controller.getVareGrupper());
        int index = lbwvareGruppe.getItems().size() -1;
        lbwvareGruppe.getSelectionModel().select(index);



    }
}
