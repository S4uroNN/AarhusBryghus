package gui;

import application.controller.SalgController;
import application.controller.VareController;
import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.time.LocalDate;
import java.util.Optional;

public class
SalgPane extends GridPane {
    private ListView<Ordrelinje> lvwOrdre = new ListView<>();
    private Button btnTilføjVare, btnFjernVare, btnStartSalg, btnAfslutSalg;
    private ComboBox<Prisliste> prislisteComboBox;
    private RadioButton rbDankort, rbMobilepay, rbKontant, rbRegning, rbKlippekort;
    private RadioButton rbFast, rbProcent, rbUserDefined, rbNoDiscount;
    private TextField txfRabat, txfSamletPris;
    private SalgController salgController;
    private VareController vareController;
    private Dagsproduktion dagsproduktion;
    private Salg salg;
    private Storage storage = Storage.getInstance();
    ToggleGroup toggleGroup = new ToggleGroup();

    public SalgPane() {
        setHgap(20);
        setVgap(10);
        this.setPadding(new Insets(20));
        this.setGridLinesVisible(false);


        salgController = SalgController.getSalgController();
        vareController = VareController.getController();
        dagsproduktion = Dagsproduktion.getDagsproduktion();

        Label lblOrdre = new Label("Ordre:");
        this.add(lblOrdre, 0, 0);
        this.add(lvwOrdre, 0, 1);


        Label lblBetalingsform = new Label("Betalingsform:");
        this.add(lblBetalingsform, 0, 2);


        rbDankort = new RadioButton("Dankort");
        rbDankort.setToggleGroup(toggleGroup);
        rbDankort.setOnAction(event -> dankortAction());
        rbMobilepay = new RadioButton("Mobliepay");
        rbMobilepay.setToggleGroup(toggleGroup);
        rbMobilepay.setOnAction(event -> mobilepayAction());
        rbKontant = new RadioButton("Kontant");
        rbKontant.setToggleGroup(toggleGroup);
        rbKontant.setOnAction(event -> kontantAction());
        rbRegning = new RadioButton("Regning");
        rbRegning.setToggleGroup(toggleGroup);
        rbRegning.setOnAction(event -> regningAction());
        rbKlippekort = new RadioButton("Klippekort");
        rbKlippekort.setToggleGroup(toggleGroup);
        rbKlippekort.setOnAction(event -> klippekortAction());
        HBox betalingsformRadioButtons = new HBox();
        betalingsformRadioButtons.getChildren().add(rbDankort);
        betalingsformRadioButtons.getChildren().add(rbMobilepay);
        betalingsformRadioButtons.getChildren().add(rbKontant);
        betalingsformRadioButtons.getChildren().add(rbRegning);
        betalingsformRadioButtons.getChildren().add(rbKlippekort);
        betalingsformRadioButtons.setSpacing(10);
        betalingsformRadioButtons.setAlignment(Pos.BOTTOM_CENTER);
        this.add(betalingsformRadioButtons, 0, 3);

        btnStartSalg = new Button("Start Salg");
        this.add(btnStartSalg, 0, 4);
        btnStartSalg.setOnAction(event -> startSalg());

        btnAfslutSalg = new Button("Afslut Salg");
        this.add(btnAfslutSalg, 2,4);
        btnAfslutSalg.setOnAction(event -> afslutSalg());



        btnTilføjVare = new Button("Tilføj");
        btnTilføjVare.setOnAction(event -> tilføjVareAction());
        btnFjernVare = new Button("Fjern");
        btnFjernVare.setOnAction(event -> fjernVareAction());
        VBox OrdreButtons = new VBox();
        OrdreButtons.getChildren().add(btnTilføjVare);
        OrdreButtons.getChildren().add(btnFjernVare);
        OrdreButtons.setSpacing(10);
        OrdreButtons.setAlignment(Pos.TOP_CENTER);
        OrdreButtons.prefWidth(500);
        this.add(OrdreButtons, 1, 1);

        ToggleGroup toggleGroupRabat = new ToggleGroup();

        Label lblPrisliste = new Label("Prisliste:");

        prislisteComboBox = new ComboBox<>();
        prislisteComboBox.getItems().setAll(storage.getPrislister());

        Label lblRabat = new Label("Rabat:");
        HBox rabatBox = new HBox();
        rbFast = new RadioButton("Fast");
        rbFast.setToggleGroup(toggleGroupRabat);
        rbProcent = new RadioButton("Procent");
        rbProcent.setToggleGroup(toggleGroupRabat);
        rbUserDefined = new RadioButton("User Defined");
        rbUserDefined.setToggleGroup(toggleGroupRabat);
        rbNoDiscount = new RadioButton("No Discount");
        rbNoDiscount.setToggleGroup(toggleGroupRabat);
        txfRabat = new TextField();
        Label lblSamletPris = new Label("Samlet Pris:");
        txfSamletPris = new TextField();
        txfSamletPris.setEditable(false);
        VBox prisBox = new VBox();
        prisBox.getChildren().add(lblPrisliste);
        prisBox.getChildren().add(prislisteComboBox);
        prisBox.getChildren().add(lblRabat);
        prisBox.getChildren().add(rabatBox);
        rabatBox.getChildren().add(rbFast);
        rabatBox.getChildren().add(rbProcent);
        rabatBox.getChildren().add(rbUserDefined);
        rabatBox.getChildren().add(rbNoDiscount);
        rabatBox.setSpacing(10);
        rabatBox.setAlignment(Pos.TOP_CENTER);
        prisBox.getChildren().add(txfRabat);
        prisBox.getChildren().add(lblSamletPris);
        prisBox.getChildren().add(txfSamletPris);
        prisBox.setSpacing(20);
        prisBox.setAlignment(Pos.TOP_LEFT);
        this.add(prisBox, 2, 1);
    }

    private void startSalg() {
        Prisliste prisliste = prislisteComboBox.getSelectionModel().getSelectedItem();
        if (prisliste != null){
            salg = salgController.createSalg(salgController.getDagsproduktion(),prisliste);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Salg Oprettet!");
            alert.showAndWait();
            btnStartSalg.setDisable(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Prisliste er ikke valgt!");
            alert.showAndWait();
        }

    }
    private void afslutSalg(){
        if (salg.getBetalingsform() != null) {
            lvwOrdre.getItems().clear();
            txfSamletPris.clear();
            toggleGroup.selectToggle(null);
            btnStartSalg.setDisable(false);
            prislisteComboBox.getSelectionModel().clearSelection();
            salgController.updateOmsætning(dagsproduktion);
            salg = null;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Salg er afsluttet");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Betalingsform er ikke valgt!");
            alert.showAndWait();
        }
    }
    private void tilføjVareAction(){
        if (salg != null) {
            TilføjTilSalgOrdreWindow dia = new TilføjTilSalgOrdreWindow("Tilføj vare til ordre", salg);
            dia.showAndWait();
            lvwOrdre.getItems().setAll(salg.getOrdrelinjer());
            txfSamletPris.setText(String.valueOf(salg.samletPris()));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Salg er ikke oprettet!");
            alert.showAndWait();

        }
    }
    private void fjernVareAction(){
        Ordrelinje ordre = lvwOrdre.getSelectionModel().getSelectedItem();
        if (ordre != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Vare fra ordre");
            // alert.setContentText("Are you sure?");
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();

            // Wait for the modal dialog to close

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                salgController.removeOrdrelinjeSalg(salg,ordre);
                lvwOrdre.getItems().setAll(salg.getOrdrelinjer());
                txfSamletPris.setText(String.valueOf(salg.samletPris()));
            }
        }

    }




    private void dankortAction(){
        if (rbDankort.isSelected() && salg != null){
            salg.setBetalingsform(Betalingsform.DANKORT);
        } else {
            toggleGroup.selectToggle(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Salg er ikke oprettet!");
            alert.showAndWait();
        }
    }
    private void mobilepayAction(){
        if (rbMobilepay.isSelected() && salg != null){
            salg.setBetalingsform(Betalingsform.MOBILEPAY);
        } else {
            toggleGroup.selectToggle(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Salg er ikke oprettet!");
            alert.showAndWait();
        }
    }
    private void kontantAction(){
        if (rbKontant.isSelected() && salg != null){
            salg.setBetalingsform(Betalingsform.KONTANT);
        } else {
            toggleGroup.selectToggle(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Salg er ikke oprettet!");
            alert.showAndWait();
        }
    }
    private void regningAction(){
        if (rbRegning.isSelected() && salg != null){
            salg.setBetalingsform(Betalingsform.REGNING);
        } else {
            toggleGroup.selectToggle(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Salg er ikke oprettet!");
            alert.showAndWait();
        }
    }
    private  void klippekortAction(){
        if (rbKlippekort.isSelected() && salg != null){
            salg.setBetalingsform(Betalingsform.KLIPPEKORT);
        }else {
            toggleGroup.selectToggle(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Salg er ikke oprettet!");
            alert.showAndWait();
        }
    }

}