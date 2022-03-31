package gui;

import application.model.Ordrelinje;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

import javax.swing.plaf.basic.BasicViewportUI;
import java.time.LocalDate;
import java.time.LocalTime;

public class UdlejningPane extends GridPane {
    private ListView<Ordrelinje> lvwOrder = new ListView<>();
    private TextField txfStartDato, txfNavn, txfEmail, txfTlfnr, txfRabat, txfPris;
    private DatePicker dtpSlutDato;
    private Button btnTilføj, btnFjern;
    private RadioButton rdbDankort, rdbMobilepay, rdbKontant, rdbRegning, rdbKlippekort;
    private RadioButton rdbFast, rdbProcent, rdbUserDefined, rdbNoRabat;
    private ComboBox cbPrisliste;

    private Storage storage = Storage.getInstance();

    public UdlejningPane() {

        this.setPadding(new Insets(20));
        setHgap(20);
        setHgap(10);
        setGridLinesVisible(false);

        Label lblOrder = new Label("Order");
        this.add(lblOrder, 0, 0);
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

        btnFjern = new Button("Tilføj");
        btnFjern.setPrefWidth(100);
        btnTilføj = new Button("Fjern");
        btnTilføj.setPrefWidth(100);

        cbPrisliste = new ComboBox<>();
        cbPrisliste.getItems().setAll(storage.getVarer());

        VBox vBoxLabel = new VBox();
        vBoxLabel.setSpacing(15);
        vBoxLabel.getChildren().add(new Label("Priliste"));
        vBoxLabel.getChildren().add(new Label("StartDato: "));
        vBoxLabel.getChildren().add(new Label("SlutDato: "));
        vBoxLabel.getChildren().add(new Label("Navn: "));
        vBoxLabel.getChildren().add(new Label("Email: "));
        vBoxLabel.getChildren().add(new Label("Telefon Nr: "));

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
        vboxTilføj.getChildren().add(btnTilføj);
        vboxTilføj.getChildren().add(btnFjern);
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
        rdbProcent = new RadioButton("Procent");
        rdbProcent.setToggleGroup(toggleGroupRabat);
        rdbUserDefined = new RadioButton("Andet");
        rdbUserDefined.setToggleGroup(toggleGroupRabat);
        rdbNoRabat = new RadioButton("Ingen Rabat");
        rdbNoRabat.setToggleGroup(toggleGroupRabat);


        rdbDankort = new RadioButton("Dankort");
        rdbDankort.setToggleGroup(toggleGroup);
        rdbKontant = new RadioButton("Kontant");
        rdbKontant.setToggleGroup(toggleGroup);
        rdbMobilepay = new RadioButton("Mobilepay");
        rdbMobilepay.setToggleGroup(toggleGroup);
        rdbRegning = new RadioButton("Regning");
        rdbRegning.setToggleGroup(toggleGroup);
        rdbKlippekort = new RadioButton("Klippekort");
        rdbKlippekort.setToggleGroup(toggleGroup);

        txfPris = new TextField();

        HBox hboxRadio = new HBox();
        hboxRadio.setSpacing(10);
        hboxRadio.getChildren().add(rdbDankort);
        hboxRadio.getChildren().add(rdbMobilepay);
        hboxRadio.getChildren().add(rdbRegning);
        hboxRadio.getChildren().add(rdbKontant);
        hboxRadio.getChildren().add(rdbKlippekort);

        HBox hBoxRadioRabat = new HBox();
        hBoxRadioRabat.setSpacing(10);
        hBoxRadioRabat.getChildren().add(rdbFast);
        hBoxRadioRabat.getChildren().add(rdbProcent);
        hBoxRadioRabat.getChildren().add(rdbUserDefined);
        hBoxRadioRabat.getChildren().add(rdbNoRabat);

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
        this.add(vboxRabat,2,2);


    }
}
