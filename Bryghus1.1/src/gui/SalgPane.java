package gui;

import application.controller.SalgController;
import application.model.Prisliste;
import application.model.Salg;
import application.model.Vare;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

public class SalgPane extends GridPane {
    private ListView<Vare> lvwOrdre = new ListView<>();
    private Button btnTilføjVare, btnFjernVare;
    private ComboBox<Prisliste> prislisteComboBox;
    private RadioButton rbDankort, rbMobilepay, rbKontant, rbRegning, rbKlippekort;
    private RadioButton rbFastProcent, rbUserDefined, rbNoDiscount;
    private TextField txfRabat, txfSamletPris;
    private SalgController salgController;
    private Storage storage = Storage.getInstance();
    public SalgPane(){
        setHgap(20);
        setVgap(10);
        this.setPadding(new Insets(20));
        this.setGridLinesVisible(false);

        salgController = SalgController.getSalgController();

        Label lblOrdre = new Label("Ordre:");
        this.add(lblOrdre,0,0);

        this.add(lvwOrdre,0,1);

        Label lblBetalingsform = new Label("Betalingsform:");
        this.add(lblBetalingsform,0,2);

        rbDankort = new RadioButton("Dankort");
        rbMobilepay = new RadioButton("Mobliepay");
        rbKontant = new RadioButton("Kontant");
        rbRegning = new RadioButton("Regning");
        rbKlippekort = new RadioButton("Klippekort");
        HBox betalingsformRadioButtons = new HBox();
        betalingsformRadioButtons.getChildren().add(rbDankort);
        betalingsformRadioButtons.getChildren().add(rbMobilepay);
        betalingsformRadioButtons.getChildren().add(rbKontant);
        betalingsformRadioButtons.getChildren().add(rbRegning);
        betalingsformRadioButtons.getChildren().add(rbKlippekort);
        betalingsformRadioButtons.setSpacing(10);
        betalingsformRadioButtons.setAlignment(Pos.BOTTOM_CENTER);
        this.add(betalingsformRadioButtons,0,3);

        btnTilføjVare = new Button("Tilføj");
        btnFjernVare = new Button("Fjern");
        VBox OrdreButtons = new VBox();
        OrdreButtons.getChildren().add(btnTilføjVare);
        OrdreButtons.getChildren().add(btnFjernVare);
        OrdreButtons.setSpacing(10);
        OrdreButtons.setAlignment(Pos.TOP_CENTER);
        OrdreButtons.prefWidth(500);
        this.add(OrdreButtons,1,1);

        Label lblPrisliste = new Label("Prisliste:");
        prislisteComboBox = new ComboBox<>();
        Label lblRabat = new Label("Rabat:");
        HBox rabatBox = new HBox();
        rbFastProcent = new RadioButton("Fast Procent");
        rbUserDefined = new RadioButton("User Defined");
        rbNoDiscount = new RadioButton("No Discount");
        txfRabat = new TextField();
        Label lblSamletPris = new Label("Samlet Pris:");
        txfSamletPris = new TextField();
        txfSamletPris.setEditable(false);
        VBox prisBox = new VBox();
        prisBox.getChildren().add(lblPrisliste);
        prisBox.getChildren().add(prislisteComboBox);
        prisBox.getChildren().add(lblRabat);
        prisBox.getChildren().add(rabatBox);
        rabatBox.getChildren().add(rbFastProcent);
        rabatBox.getChildren().add(rbUserDefined);
        rabatBox.getChildren().add(rbNoDiscount);
        rabatBox.setSpacing(20);
        rabatBox.setAlignment(Pos.TOP_CENTER);
        prisBox.getChildren().add(txfRabat);
        prisBox.getChildren().add(lblSamletPris);
        prisBox.getChildren().add(txfSamletPris);
        prisBox.setSpacing(20);
        prisBox.setAlignment(Pos.TOP_LEFT);
        this.add(prisBox,2,1);














    }
}
