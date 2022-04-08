package gui;

import application.controller.SalgController;
import application.controller.VareController;
import application.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



import java.util.Optional;

public class SalgPane extends GridPane {
    private ListView<Ordrelinje> lvwOrdre = new ListView<>();
    private Button btnTilføjVare, btnFjernVare, btnStartSalg, btnAfslutSalg,btnPrisKorek;

    private RadioButton rbDankort, rbMobilepay, rbKontant, rbRegning, rbKlippekort;
    private RadioButton rbFast, rbProcent;
    private TextField txfRabat, txfSamletPris, txfKlipPris;
    private ToggleGroup toggleGroup = new ToggleGroup();
    private ToggleGroup toggleGroupRabat = new ToggleGroup();

    private SalgController salgController = SalgController.getSalgController();
    private VareController vareController = VareController.getController();

    private ComboBox<Prisliste> prislisteComboBox;
    private Salg salg;
    private Prisliste prisliste;


    public SalgPane() {
        setHgap(20);
        setVgap(10);
        this.setPadding(new Insets(20));
        this.setGridLinesVisible(false);

        salgController.loadStorage();

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
        this.add(betalingsformRadioButtons, 0, 3);

        btnStartSalg = new Button("Start Salg");
        this.add(btnStartSalg, 0, 4);
        btnStartSalg.setOnAction(event -> startSalg());

        btnAfslutSalg = new Button("Afslut Salg");
        this.add(btnAfslutSalg, 2,4);
        btnAfslutSalg.setOnAction(event -> afslutSalg());

        btnTilføjVare = new Button("Tilføj");
        btnTilføjVare.setPrefWidth(120);
        btnTilføjVare.setOnAction(event -> tilføjVareAction());

        btnFjernVare = new Button("Fjern");
        btnFjernVare.setPrefWidth(120);
        btnFjernVare.setOnAction(event -> fjernVareAction());

        btnPrisKorek = new Button("Ret Pris");
        btnPrisKorek.setPrefWidth(120);
        btnPrisKorek.setOnAction(event -> indsaetRabatPåVare());
        btnPrisKorek.setDisable(true);

        VBox ordreButtons = new VBox();
        ordreButtons.getChildren().add(btnTilføjVare);
        ordreButtons.getChildren().add(btnFjernVare);
        ordreButtons.getChildren().add(btnPrisKorek);
        ordreButtons.setSpacing(10);
        ordreButtons.setAlignment(Pos.TOP_CENTER);
        this.add(ordreButtons, 1, 1);


        Label lblPrisliste = new Label("Prisliste:");

        prislisteComboBox = new ComboBox<>();
        prislisteComboBox.getItems().setAll(vareController.getPrislister());

        Label lblRabat = new Label("Rabat:");

        rbFast = new RadioButton("Fast");
        rbFast.setToggleGroup(toggleGroupRabat);
        rbFast.setOnAction(event -> setFastRabatAction());
        rbFast.setDisable(true);

        rbProcent = new RadioButton("Procent");
        rbProcent.setToggleGroup(toggleGroupRabat);
        rbProcent.setOnAction(event -> setProcentRabatAction());
        rbProcent.setDisable(true);

        txfRabat = new TextField();
        txfRabat.setDisable(true);

        Label lblSamletPris = new Label("Samlet Pris:");
        txfSamletPris = new TextField();
        txfSamletPris.setEditable(false);

        txfKlipPris = new TextField();
        txfKlipPris.setEditable(false);

        VBox vboxLabel = new VBox();
        vboxLabel.setSpacing(35);
        vboxLabel.getChildren().add(lblPrisliste);
        vboxLabel.getChildren().add(lblRabat);
        vboxLabel.getChildren().add(lblSamletPris);
        vboxLabel.getChildren().add(new Label("Antal Klip:"));
        this.add(vboxLabel,2,1);

        HBox rabatBox = new HBox();
        rabatBox.getChildren().add(rbFast);
        rabatBox.getChildren().add(rbProcent);
        rabatBox.setSpacing(10);

        VBox prisBox = new VBox();
        prisBox.getChildren().add(prislisteComboBox);
        prisBox.getChildren().add(rabatBox);
        prisBox.getChildren().add(txfRabat);
        prisBox.getChildren().add(txfSamletPris);
        prisBox.getChildren().add(txfKlipPris);
        prisBox.setSpacing(15);
        this.add(prisBox, 3, 1);
    }

    private void startSalg() {
        prisliste = prislisteComboBox.getSelectionModel().getSelectedItem();
        if (prisliste != null){
            salg = salgController.createSalg(prisliste);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Salg Oprettet!");
            alert.showAndWait();
            btnStartSalg.setDisable(true);
            btnPrisKorek.setDisable(false);
            prislisteComboBox.setDisable(true);

            if(!prisliste.getNavn().equals("Fredags Cafe")){
                rbKlippekort.setDisable(true);
            }
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
            toggleGroupRabat.selectToggle(null);
            rbFast.setDisable(true);
            rbProcent.setDisable(true);
            btnStartSalg.setDisable(false);
            rbKlippekort.setDisable(false);
            txfRabat.clear();
            prislisteComboBox.setDisable(false);
            prislisteComboBox.getSelectionModel().clearSelection();
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
        prisliste = prislisteComboBox.getSelectionModel().getSelectedItem();
        if (salg != null) {
            TilføjTilSalgOrdreWindow dia = new TilføjTilSalgOrdreWindow(salg, prisliste);
            dia.showAndWait();
            lvwOrdre.getItems().setAll(salgController.getSalgOrdreLinjer(salg));
            txfSamletPris.setText(String.valueOf(salgController.getSamletPris(salg)));
            txfKlipPris.setText(salgController.getSamletPrisKlip(salg)+ "");
            rbProcent.setDisable(false);
            rbFast.setDisable(false);
            txfRabat.setDisable(false);
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
            alert.setHeaderText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                salgController.removeOrdrelinjeSalg(salg,ordre);
                lvwOrdre.getItems().setAll(salgController.getSalgOrdreLinjer(salg));
                txfSamletPris.setText(String.valueOf(salgController.getSamletPris(salg)));
            }
        }

    }

    private void indsaetRabatPåVare(){
        Ordrelinje ordrelinje = lvwOrdre.getSelectionModel().getSelectedItem();

        if(ordrelinje != null){
            PriskorrektionWindow pris = new PriskorrektionWindow(ordrelinje);
            pris.showAndWait();
            txfSamletPris.setText(salgController.getSamletPris(salg) + "");
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

    private void setFastRabatAction(){
        if(rbFast.isSelected() && !(txfRabat.getText().equals(""))){
            salgController.setFastRabatSalg(salg, Double.parseDouble(txfRabat.getText()));
            txfSamletPris.setText(salgController.getSamletPris(salg) + "");
        }
    }

    private void setProcentRabatAction(){
        if(rbProcent.isSelected() && !(txfRabat.getText().equals(""))){
            salgController.setProcentRabatSalg(salg, Double.parseDouble(txfRabat.getText()));
            txfSamletPris.setText(salgController.getSamletPris(salg) + "");
        }
    }
}