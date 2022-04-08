package gui;

import application.controller.SalgController;
import application.model.Ordrelinje;
import application.model.Rabat;
import application.model.Salg;
import application.model.Vare;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PriskorrektionWindow extends Stage {
    private TextField txfRabat, txfVare, txfPris;
    private RadioButton rdbFast, rdbProcent;
    private Button btnTilføj, btnAfbryd;
    private Ordrelinje ordrelinje;

    private SalgController salgController = SalgController.getSalgController();


    public PriskorrektionWindow(Ordrelinje ordrelinje){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.ordrelinje = ordrelinje;

        this.setTitle("Priskorrektion");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);


        txfPris = new TextField();
        txfVare = new TextField();

        if(ordrelinje != null){
            txfVare.setText(String.valueOf(ordrelinje.getVare()));
        }

        btnAfbryd = new Button("Afbryd");
        btnAfbryd.setOnAction(event -> this.hide());
        btnTilføj = new Button("Tilføj");
        btnTilføj.setOnAction(event -> tilføjRabat());

        HBox hboxBtn = new HBox();
        hboxBtn.getChildren().add(btnTilføj);
        hboxBtn.getChildren().add(btnAfbryd);

        pane.add(new Label("Vare"),0,0);
        pane.add(txfVare,0,1);
        pane.add(new Label("Pris"),0,2);
        pane.add(txfPris,0,3);
        pane.add(hboxBtn,0,5);

        ToggleGroup toggleGroup = new ToggleGroup();


        rdbFast = new RadioButton("Fast");
        rdbProcent = new RadioButton("Procent");
        rdbProcent.setToggleGroup(toggleGroup);
        rdbFast.setToggleGroup(toggleGroup);

        HBox hboxradio = new HBox();
        hboxradio.setSpacing(5);
        hboxradio.getChildren().add(rdbFast);
        hboxradio.getChildren().add(rdbProcent);
        pane.add(hboxradio,0,4);


    }

    public void stop() {
        salgController.saveStorage();
    }


    private void tilføjRabat(){
        if(rdbFast.isSelected()){
            if(Double.parseDouble(txfPris.getText().trim()) <=0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Rabat skal være større en 0");
                alert.showAndWait();
            }else{
                double beloeb = Double.parseDouble(txfPris.getText().trim());
                salgController.setFastRabatOrdrelinje(ordrelinje,beloeb);
            }
        } else if(rdbProcent.isSelected()){
            double beloeb = Double.parseDouble(txfPris.getText().trim());
            salgController.setProcentRabatOrdrelinje(ordrelinje,beloeb);
        }
        this.hide();
    }
}
