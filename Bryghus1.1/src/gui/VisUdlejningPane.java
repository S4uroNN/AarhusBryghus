package gui;

import application.controller.SalgController;
import application.model.Betalingsform;
import application.model.Dagsproduktion;
import application.model.Udlejning;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import storage.Storage;


public class VisUdlejningPane extends GridPane {
    private ListView<Udlejning> lvwAfsluttedeUdlejninger = new ListView();
    private ListView<Udlejning> lvwaktiveUdlejninger = new ListView<>();

    private TextField txfNavn, txfEmail, txfTlfNr, txfStartDato, txfSlutDato;
    private Button btnAfslut,btnOpdater;
    private DatePicker dpSlut;
    private TextArea txaLinjer;


    private Storage storage = Storage.getInstance();
    private Dagsproduktion dagsproduktion = SalgController.getDagsproduktion();
    private SalgController salgController = SalgController.getSalgController();

    private Udlejning udlejning;
    public VisUdlejningPane() {

        this.setPadding(new Insets(20));
        setHgap(20);
        setHgap(10);
        setGridLinesVisible(false);
        this.add(new Label("Aktive Udlejninger"),0,0);
        this.add(lvwaktiveUdlejninger,0,1);
        lvwaktiveUdlejninger.getItems().setAll(storage.getAktiveUdlejninger());

        this.add(new Label("Afsluttede Udlejninger"),1,0);
        this.add(lvwAfsluttedeUdlejninger,1,1);
        lvwAfsluttedeUdlejninger.getItems().setAll(dagsproduktion.getDagensAfsluttedeUdlejninger());

        ChangeListener<Udlejning> listener = (ov, oldArrangement, newArrangement) -> this.selectedAfsluttetUdlejningChanged();
        lvwAfsluttedeUdlejninger.getSelectionModel().selectedItemProperty().addListener(listener);

        ChangeListener<Udlejning> listener1 = (ov, oldArrangement, newArrangement) -> this.selectedAktiveUdlejningChanged();
        lvwaktiveUdlejninger.getSelectionModel().selectedItemProperty().addListener(listener1);

        btnAfslut = new Button("Afslut Udlejning");
        btnAfslut.setOnAction(event -> afslutUdlejningAction());
        btnOpdater = new Button("Opdater");
        btnOpdater.setOnAction(event -> updatelists());

        txfNavn = new TextField();
        txfEmail = new TextField();
        txfStartDato = new TextField();
        txfTlfNr = new TextField();
        txfSlutDato = new TextField();

        dpSlut = new DatePicker();
        txaLinjer = new TextArea();
        txaLinjer.setPrefWidth(200);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(txfNavn);
        vBox.getChildren().add(txfTlfNr);
        vBox.getChildren().add(txfEmail);
        vBox.getChildren().add(txfStartDato);
        vBox.getChildren().add(dpSlut);
        vBox.getChildren().add(new Label("Ordre linjer: "));
        vBox.getChildren().add(txaLinjer);
        vBox.getChildren().add(btnAfslut);

        this.add(vBox,3,1);

        VBox vBoxlabel = new VBox();
        vBoxlabel.setSpacing(20);
        vBoxlabel.getChildren().add(new Label("Navn:"));
        vBoxlabel.getChildren().add(new Label("Tlf nr:"));
        vBoxlabel.getChildren().add(new Label("Email:"));
        vBoxlabel.getChildren().add(new Label("Start Dato:"));
        vBoxlabel.getChildren().add(new Label("Slut Dato:"));
        vBoxlabel.getChildren().add(btnOpdater);
        this.add(vBoxlabel,2,1);

    }
    private void updatelists(){
        lvwaktiveUdlejninger.getItems().setAll(storage.getAktiveUdlejninger());
        lvwAfsluttedeUdlejninger.getItems().setAll(dagsproduktion.getDagensAfsluttedeUdlejninger());
    }

    private void selectedAktiveUdlejningChanged() {
        Udlejning udlejning = lvwaktiveUdlejninger.getSelectionModel().getSelectedItem();

        if(udlejning != null){
            txfNavn.clear();
            txfEmail.clear();
            txfStartDato.clear();
            txfTlfNr.clear();
            dpSlut.setValue(null);

            txfNavn.setText(udlejning.getKontaktPerson());
            txfEmail.setText(udlejning.getEmail());
            txfStartDato.setText(udlejning.getStartDato() + "");
            txfTlfNr.setText(udlejning.getTelefonnr());
            txaLinjer.setText(String.valueOf(udlejning.getOrdrelinjer()));
            btnAfslut.setDisable(false);
        }
    }

    private void selectedAfsluttetUdlejningChanged() {
        Udlejning udlejning = lvwAfsluttedeUdlejninger.getSelectionModel().getSelectedItem();

        if(udlejning != null){
            txfNavn.setText(udlejning.getKontaktPerson());
            txfEmail.setText(udlejning.getEmail());
            txfStartDato.setText(udlejning.getStartDato() + "");
            dpSlut.setValue(udlejning.getSlutDato());
            txfTlfNr.setText(udlejning.getTelefonnr());
            txaLinjer.setText(String.valueOf(udlejning.getOrdrelinjer()));
            btnAfslut.setDisable(true);
        }

    }
    private void afslutUdlejningAction() {
        udlejning = lvwaktiveUdlejninger.getSelectionModel().getSelectedItem();
        salgController.afslutUdlejning(udlejning,dagsproduktion,udlejning.getBetalingsform());

        updatelists();
    }


}
