package gui;

import application.controller.SalgController;
import application.controller.VareController;
import application.model.Prisliste;
import application.model.Salg;
import application.model.Vare;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.time.LocalDate;
import java.util.ArrayList;

public class TilføjTilSalgOrdreWindow extends Stage {
    private Vare vare;
    private Salg salg;
    private Prisliste prisliste;
    private SalgController salgController = SalgController.getSalgController();
    private VareController vareController = VareController.getController();


    private ListView<Vare> lvwVare = new ListView<>();
    private TextField txfAntal;
    private Button btnTilføj, btnFortryd;

    private Label lblError;

    private ArrayList<Vare> varer = new ArrayList<>();

    public TilføjTilSalgOrdreWindow(Salg salg, Prisliste prisliste){
        this.setTitle("Tilføj Vare");
        this.salg = salg;
        this.prisliste = prisliste;
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.initContent(pane);

    }



    private void initContent(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        pane.setPrefWidth(500);

        salgController.loadStorage();
        vareController.loadStorage();

        pane.add(new Label("Antal:"),1 ,0);
        txfAntal = new TextField();
        pane.add(txfAntal,1,1);
        txfAntal.setAlignment(Pos.TOP_CENTER);

        pane.add(new Label("Vare:"),0,0);
        pane.add(lvwVare,0,1);
        pane.add(new Label(prisliste +""),5,5);

        if(prisliste != null){
            lvwVare.getItems().setAll(prisliste.getTilføjedeVarer());
        }

        ChangeListener<Vare> vareListener = (ov, oldVare, newVare) -> this.selectedVareChanged();
        lvwVare.getSelectionModel().selectedItemProperty().addListener(vareListener);

        btnTilføj = new Button("Tilføj");
        btnFortryd = new Button("Fortryd");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().add(btnTilføj);
        hBox.getChildren().add(btnFortryd);
        pane.add(hBox,0,2);

        btnTilføj.setOnAction(event -> tilføjVare());
        btnFortryd.setOnAction(event -> this.hide());

        lblError = new Label();
        pane.add(lblError,0,3);
        lblError.setStyle("-fx-text-fill: red");
    }

    public void stop() {
        vareController.saveStorage();
    }

    private void tilføjVare() {
        lblError.setText("");
        Vare vare = lvwVare.getSelectionModel().getSelectedItem();
        try {
        int antal = Integer.parseInt(txfAntal.getText().trim());
            if (vare != null) {
                if (antal <= 0) {
                    lblError.setText("Antal skal være større end 0");
                } else {
                    salgController.createOrdrelinjeSalg(salg, antal, vare);
                    this.hide();
                }

            }

        } catch (NumberFormatException e){
            txfAntal.clear();
            lblError.setText("Antal er ikke korrekt format");
        }
    }
    private void selectedVareChanged(){
        Vare selected = lvwVare.getSelectionModel().getSelectedItem();
    }

}
