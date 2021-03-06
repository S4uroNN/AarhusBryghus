package gui;

import application.controller.SalgController;
import application.controller.VareController;
import application.model.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class UdlejningPane extends GridPane {
    private ListView<Ordrelinje> lvwOrder = new ListView<>();
    private TextField txfStartDato, txfNavn, txfEmail, txfTlfnr, txfRabat, txfPris;
    private DatePicker dtpSlutDato;
    private Button btnTilføj, btnFjern, btnOpretUdlejning, btnAfslutUdlejning, btnNyUdlejning,btnRetPris;
    private RadioButton rdbDankort, rdbMobilepay, rdbKontant, rdbRegning;
    private RadioButton rdbFast, rdbProcent;
    private ComboBox<Prisliste> cbPrisliste;

    private SalgController salgController = SalgController.getSalgController();
    private VareController vareController = VareController.getController();
    private Udlejning udlejning;
    private Prisliste prisliste;

    public UdlejningPane() {

        this.setPadding(new Insets(20));
        setHgap(20);
        setHgap(10);
        setGridLinesVisible(false);


        this.add(new Label("Ordre"), 0, 0);
        this.add(lvwOrder, 0, 1);

        txfStartDato = new TextField();
        txfStartDato.setText(LocalDate.now() + "");
        txfStartDato.setDisable(true);
        txfNavn = new TextField();
        txfEmail = new TextField();
        txfTlfnr = new TextField();
        txfPris = new TextField();
        txfRabat = new TextField();
        txfRabat.setDisable(false);

        dtpSlutDato = new DatePicker();


        btnFjern = new Button("Fjern");
        btnFjern.setPrefWidth(120);
        btnFjern.setOnAction(event -> fjernVareAction());
        btnFjern.setDisable(true);
        btnTilføj = new Button("Tilføj");
        btnTilføj.setPrefWidth(120);
        btnTilføj.setOnAction(event -> tilføjVareAction());
        btnTilføj.setDisable(true);
        btnOpretUdlejning = new Button("Opret Udlejning");
        btnOpretUdlejning.setPrefWidth(120);
        btnOpretUdlejning.setOnAction(event -> opretUdlejningAction());
        btnAfslutUdlejning = new Button("Afslut Udlejning");
        btnAfslutUdlejning.setPrefWidth(120);
        btnAfslutUdlejning.setOnAction(event -> afslutUdlejningAction());
        btnAfslutUdlejning.setDisable(true);
        btnNyUdlejning = new Button("Ny Udlejning");
        btnNyUdlejning.setPrefWidth(120);
        btnNyUdlejning.setDisable(true);
        btnNyUdlejning.setOnAction(event -> nyUdlejningAction());
        btnRetPris = new Button("Ret pris");
        btnRetPris.setPrefWidth(120);
        btnRetPris.setDisable(true);
        btnRetPris.setOnAction(event -> indsaetRabatPåVare());



        cbPrisliste = new ComboBox<>();
        cbPrisliste.getItems().setAll(vareController.getPrislister());


        VBox vBoxLabel = new VBox();
        vBoxLabel.setSpacing(15);
        vBoxLabel.getChildren().add(new Label("Prisliste"));
        vBoxLabel.getChildren().add(new Label("StartDato:"));
        vBoxLabel.getChildren().add(new Label("SlutDato:"));
        vBoxLabel.getChildren().add(new Label("Navn:"));
        vBoxLabel.getChildren().add(new Label("Email:"));
        vBoxLabel.getChildren().add(new Label("Telefon Nr:"));

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().add(cbPrisliste);
        vBox.getChildren().add(txfStartDato);
        vBox.getChildren().add(dtpSlutDato);
        vBox.getChildren().add(txfNavn);
        vBox.getChildren().add(txfEmail);
        vBox.getChildren().add(txfTlfnr);

        VBox vboxTilføj = new VBox();
        vboxTilføj.setSpacing(10);
        vboxTilføj.getChildren().add(btnOpretUdlejning);
        vboxTilføj.getChildren().add(btnTilføj);
        vboxTilføj.getChildren().add(btnFjern);
        vboxTilføj.getChildren().add(btnAfslutUdlejning);
        vboxTilføj.getChildren().add(btnNyUdlejning);
        vboxTilføj.getChildren().add(btnRetPris);
        this.add(vboxTilføj, 1, 1);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().add(vBoxLabel);
        hBox.getChildren().add(vBox);
        this.add(hBox, 2, 1);

        ToggleGroup toggleGroup = new ToggleGroup();
        ToggleGroup toggleGroupRabat = new ToggleGroup();

        rdbFast = new RadioButton("Fast");
        rdbFast.setToggleGroup(toggleGroupRabat);
        rdbFast.setOnAction(event -> setFastRabatAction());
        rdbFast.setDisable(true);
        rdbProcent = new RadioButton("Procent");
        rdbProcent.setOnAction(event -> setProcentRabatAction());
        rdbProcent.setToggleGroup(toggleGroupRabat);
        rdbProcent.setDisable(true);
        txfRabat.setDisable(true);


        rdbDankort = new RadioButton("Dankort");
        rdbDankort.setToggleGroup(toggleGroup);
        rdbKontant = new RadioButton("Kontant");
        rdbKontant.setToggleGroup(toggleGroup);
        rdbMobilepay = new RadioButton("Mobilepay");
        rdbMobilepay.setToggleGroup(toggleGroup);
        rdbRegning = new RadioButton("Regning");
        rdbRegning.setToggleGroup(toggleGroup);

        txfPris = new TextField();

        HBox hboxRadio = new HBox();
        hboxRadio.setSpacing(10);
        hboxRadio.getChildren().add(rdbDankort);
        hboxRadio.getChildren().add(rdbMobilepay);
        hboxRadio.getChildren().add(rdbRegning);
        hboxRadio.getChildren().add(rdbKontant);

        HBox hBoxRadioRabat = new HBox();
        hBoxRadioRabat.setSpacing(10);
        hBoxRadioRabat.getChildren().add(rdbFast);
        hBoxRadioRabat.getChildren().add(rdbProcent);

        VBox vboxBetaling = new VBox();
        vboxBetaling.setSpacing(10);
        vboxBetaling.getChildren().add(new Label("Betalingsform"));
        vboxBetaling.getChildren().add(hboxRadio);
        vboxBetaling.getChildren().add(new Label("Samlet pris"));
        vboxBetaling.getChildren().add(txfPris);
        this.add(vboxBetaling, 0, 2);

        VBox vboxRabat = new VBox();
        vboxRabat.setSpacing(10);
        vboxRabat.getChildren().add(new Label("Type Rabat"));
        vboxRabat.getChildren().add(hBoxRadioRabat);
        vboxRabat.getChildren().add(new Label("Rabat"));
        vboxRabat.getChildren().add(txfRabat);
        this.add(vboxRabat, 2, 2);


    }

    public void stop() {
        salgController.saveStorage();
    }

    private void opretUdlejningAction() {
        LocalDate startDato = LocalDate.parse(txfStartDato.getText().trim());
        LocalDate slutDato = dtpSlutDato.getValue();
        String kontaktPerson = txfNavn.getText().trim();
        String email = txfEmail.getText().trim();
        String telefonr = txfTlfnr.getText().trim();
        prisliste = cbPrisliste.getSelectionModel().getSelectedItem();

        if (startDato == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en startdato!");
            alert.showAndWait();
        } else if (slutDato == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en slutdato!");
            alert.showAndWait();
        } else if (kontaktPerson.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en kontaktperson!");
            alert.showAndWait();
        } else if (email.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en email!");
            alert.showAndWait();
        } else if (telefonr.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en telefon nummer!");
            alert.showAndWait();
        } else if (prisliste == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en prisliste!");
            alert.showAndWait();
        } else {
            udlejning = salgController.createUdlejning(startDato, slutDato, kontaktPerson, email, telefonr, prisliste);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Udlejning oprettet");
            alert.showAndWait();
            btnTilføj.setDisable(false);
            btnFjern.setDisable(false);
            btnAfslutUdlejning.setDisable(false);
            btnNyUdlejning.setDisable(false);
            btnRetPris.setDisable(false);
            rdbFast.setDisable(false);
            rdbProcent.setDisable(false);
            txfRabat.setDisable(false);
            cbPrisliste.setDisable(true);

        }


    }

    private void afslutUdlejningAction() {
        Betalingsform betalingsform = null;
        if (rdbDankort.isSelected()) {
            betalingsform = Betalingsform.DANKORT;
        } else if (rdbMobilepay.isSelected()) {
            betalingsform = Betalingsform.MOBILEPAY;
        } else if (rdbRegning.isSelected()) {
            betalingsform = Betalingsform.REGNING;
        } else if (rdbKontant.isSelected()) {
            betalingsform = Betalingsform.KONTANT;
        }

        salgController.afslutUdlejning(udlejning,betalingsform);
            lvwOrder.getItems().clear();
            txfEmail.clear();
            txfNavn.clear();
            txfPris.clear();
            txfRabat.clear();
            txfTlfnr.clear();
            txfEmail.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Udlejning afsluttet");
            alert.showAndWait();
        }


    private void nyUdlejningAction() {
        Betalingsform betalingsform = null;
        if (rdbDankort.isSelected()) {
            betalingsform = Betalingsform.DANKORT;
        } else if (rdbMobilepay.isSelected()) {
            betalingsform = Betalingsform.MOBILEPAY;
        } else if (rdbRegning.isSelected()) {
            betalingsform = Betalingsform.REGNING;
        } else if (rdbKontant.isSelected()) {
            betalingsform = Betalingsform.KONTANT;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Der mangler betalingsmetode");
            alert.showAndWait();
        }
        if (betalingsform != null) {
            udlejning.setBetalingsform(betalingsform);
            lvwOrder.getItems().clear();
            txfEmail.clear();
            txfNavn.clear();
            txfPris.clear();
            txfRabat.clear();
            txfTlfnr.clear();
            txfEmail.clear();
            rdbDankort.setSelected(false);
            rdbMobilepay.setSelected(false);
            rdbRegning.setSelected(false);
            rdbKontant.setSelected(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Udlejning parkeret!");
            alert.showAndWait();

        }
    }

    private void updateControls() {
        if (salgController.getUDlOrdrelinjer(udlejning).size() > 0) {
            lvwOrder.getItems().setAll(salgController.getUDlOrdrelinjer(udlejning));
        }
        txfPris.setText(salgController.getSamletPrisUDl(udlejning) + "");

    }

    private void tilføjVareAction() {
        TilføjTilOrdreWindow tilføj = new TilføjTilOrdreWindow("Test", udlejning,prisliste);
        tilføj.showAndWait();

        updateControls();
    }

    private void fjernVareAction() {
        Ordrelinje ordrelinje = lvwOrder.getSelectionModel().getSelectedItem();
        if (ordrelinje != null) {
            salgController.removeOrdrelinjeUdlejning(udlejning,ordrelinje);
        }
        updateControls();
    }

    private void indsaetRabatPåVare(){
        Ordrelinje ordrelinje = lvwOrder.getSelectionModel().getSelectedItem();

        if(ordrelinje != null){
            PriskorrektionWindow pris = new PriskorrektionWindow(ordrelinje);
            pris.showAndWait();
            txfPris.setText(salgController.getSamletPrisUDl(udlejning) + "");
        }
    }

    private void setFastRabatAction(){
        if(udlejning != null){
            if(rdbProcent.isSelected() && !(txfRabat.getText().trim().equals(""))) {
                salgController.setFastRabatUdlejning(udlejning, Double.parseDouble(txfRabat.getText()));
                updateControls();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Udlejning ikke oprettet");
            alert.showAndWait();
        }
    }

    private void setProcentRabatAction(){
        if(udlejning != null){
            if(rdbProcent.isSelected() && !(txfRabat.getText().trim().equals(""))) {
                salgController.setProcentRabatUdlejning(udlejning, Double.parseDouble(txfRabat.getText()));
                updateControls();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Udlejning ikke oprettet");
            alert.showAndWait();
        }
    }
}
