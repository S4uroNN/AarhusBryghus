package gui;

import application.controller.SalgController;
import application.controller.VareController;
import application.model.Prisliste;
import application.model.Udlejning;
import application.model.Vare;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TilføjTilOrdreWindow extends Stage {
    private Udlejning udlejning;
    private String title;

    private ComboBox cbvarer;
    private TextField txfAntal;

    private Button btnTilføj, btnAfbryd;

    private SalgController salgController = SalgController.getSalgController();
    private Prisliste prisliste;


    public TilføjTilOrdreWindow(String title, Udlejning udlejning, Prisliste prisliste) {
        this.title = title;
        this.udlejning = udlejning;
        this.prisliste = prisliste;

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.initContent(pane);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        pane.setPrefWidth(250);

        salgController.loadStorage();

        cbvarer = new ComboBox<>();
        cbvarer.getItems().setAll(prisliste.getTilføjedeVarer());

        txfAntal = new TextField();

        btnTilføj = new Button("Tilføj");
        btnTilføj.setOnAction(event -> tilføjTilOrder());

        btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(event -> this.hide());

        pane.add(new Label("Vare"), 0, 0);
        pane.add(cbvarer, 1, 0);
        pane.add(new Label("Antal"), 0, 1);
        pane.add(txfAntal, 1, 1);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(btnTilføj);
        hBox.getChildren().add(btnAfbryd);
        pane.add(hBox, 1, 2);

    }

    private void tilføjTilOrder() {
        Vare vare = (Vare) cbvarer.getSelectionModel().getSelectedItem();
        int antal = Integer.parseInt(txfAntal.getText().trim());
        if (vare != null) {
            if (antal <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Antal må ikke være 0!");
                alert.showAndWait();
            }else{
                salgController.createOrdrelinjeUdlejning(udlejning,antal,vare);
                this.hide();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare!");
            alert.showAndWait();
        }
    }

}
