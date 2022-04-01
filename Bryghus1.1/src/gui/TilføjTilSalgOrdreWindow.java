package gui;

import application.controller.SalgController;
import application.controller.VareController;
import application.model.Dagsproduktion;
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
import storage.Storage;

public class TilføjTilSalgOrdreWindow extends Stage {
    private Vare vare;
    private Storage storage;
    private Dagsproduktion dagsproduktion;
    private Salg salg;
    private SalgController salgController = SalgController.getSalgController();


    public TilføjTilSalgOrdreWindow(String title,Salg salg){
        this.setTitle("Tilføj Vare");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);
        this.initContent(pane);



    }
    public TilføjTilSalgOrdreWindow(String title) {
        this(title, null);
    }

    private ListView<Vare> lvwVare = new ListView<>();
    private TextField txfAntal;
    private Button btnTilføj, btnFortryd;

    private Label lblError;

    private void initContent(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        pane.setPrefWidth(20);

        pane.add(new Label("Antal:"),1 ,0);
        txfAntal = new TextField();
        pane.add(txfAntal,1,1);

        pane.add(new Label("Vare:"),0,0);
        lvwVare = new ListView<>();
        lvwVare.getItems().setAll(storage.getVarer());
        pane.add(lvwVare,0,1);

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
    private void tilføjVare(){
        Vare vare = lvwVare.getSelectionModel().getSelectedItem();
        int antal = Integer.parseInt(txfAntal.getText().trim());

        if (vare != null){
            if (antal <= 0) {
                lblError.setText("Antal skal være større end 0");
            } else{
                salg.createOrdreLinje(antal,vare);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare!");
            alert.showAndWait();
        }

    }
    private void selectedVareChanged(){
        Vare selected = lvwVare.getSelectionModel().getSelectedItem();
    }

}
