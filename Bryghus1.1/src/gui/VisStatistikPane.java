package gui;

import application.controller.SalgController;
import application.model.Dagsproduktion;
import application.model.Udlejning;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.time.LocalDate;

public class VisStatistikPane extends GridPane {
    private DatePicker dpStart, dpSlut, dpDag;

    private TextField txfOmsætningSalg, txfOmsætningUdl, txfAntalsalg, txfAntalUdl, txfAntalKlipKøbt, txfAntalKlipBrugt;

    private Button btnVisIkkeAfl, btnVisSpecifik, btnOpdaterSalg, getBtnOpdaterKlip;
    private int width = 110;


    private SalgController salgController = SalgController.getSalgController();
    private Storage storage = Storage.getInstance();

    public VisStatistikPane() {
        this.setPadding(new Insets(20));
        setHgap(20);
        setHgap(10);
        setGridLinesVisible(false);

        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 1;\n" +
                "-fx-border-width: 1;\n";


        //SALG-----------------------------
        dpDag = new DatePicker();
        dpDag.setPrefWidth(width);

        txfAntalsalg = new TextField();
        txfAntalsalg.setPrefWidth(width);
        txfOmsætningSalg = new TextField();
        txfOmsætningSalg.setPrefWidth(width);

        HBox hboxDato = new HBox();
        hboxDato.getChildren().add(new Label("Dag: "));
        hboxDato.getChildren().add(dpDag);

        HBox salgogdato = new HBox();
        salgogdato.setSpacing(191); //Hej Margrethe :)
        salgogdato.getChildren().add(new Label("Salg"));
        salgogdato.getChildren().add(hboxDato);


        HBox antalOgOmsætning = new HBox();
        antalOgOmsætning.setSpacing(10);
        antalOgOmsætning.getChildren().add(new Label("Antal: "));
        antalOgOmsætning.getChildren().add(txfAntalsalg);
        antalOgOmsætning.getChildren().add(new Label("Omsætning: "));
        antalOgOmsætning.getChildren().add(txfOmsætningSalg);

        VBox salgVbox = new VBox();
        salgVbox.setPadding(new Insets(5));
        salgVbox.setStyle(cssLayout);
        salgVbox.setSpacing(10);
        salgVbox.getChildren().add(salgogdato);
        salgVbox.getChildren().add(antalOgOmsætning);
        this.add(salgVbox, 0, 0);


        //UDLEJNING---------------------------

        txfAntalUdl = new TextField();
        txfAntalUdl.setPrefWidth(width);
        txfOmsætningUdl = new TextField();
        txfOmsætningUdl.setPrefWidth(width);

        btnVisIkkeAfl = new Button("Vis ikke afleverede vare");
        btnOpdaterSalg = new Button("Opdater Salg");
        btnOpdaterSalg.setOnAction(event -> updateSalg());

        HBox hboxButton = new HBox();
        hboxButton.setSpacing(10);
        hboxButton.getChildren().add(btnVisIkkeAfl);
        hboxButton.getChildren().add(btnOpdaterSalg);
        hboxButton.setAlignment(Pos.BASELINE_RIGHT);

        HBox hboxUdl = new HBox();
        hboxUdl.setSpacing(10);
        hboxUdl.getChildren().add(new Label("Antal: "));
        hboxUdl.getChildren().add(txfAntalUdl);
        hboxUdl.getChildren().add(new Label("Omsætning: "));
        hboxUdl.getChildren().add(txfOmsætningUdl);

        VBox vboxUdl = new VBox();
        vboxUdl.setStyle(cssLayout);
        vboxUdl.setPadding(new Insets(5));
        vboxUdl.setSpacing(18); // Hej Margrethe :)
        vboxUdl.getChildren().add(new Label("Udlejninger"));
        vboxUdl.getChildren().add(hboxUdl);
        vboxUdl.getChildren().add(hboxButton);
        this.add(vboxUdl, 1, 0);

        //KLIPPEKORT--------------------------------
        dpStart = new DatePicker();
        dpSlut = new DatePicker();

        txfAntalKlipKøbt = new TextField();
        txfAntalKlipBrugt = new TextField();

        btnVisSpecifik = new Button("Vis specifikke vare");

        HBox hboxKlip = new HBox();
        hboxKlip.setSpacing(10);
        hboxKlip.getChildren().add(new Label("Klippekort"));
        hboxKlip.getChildren().add(new Label("Fra: "));
        hboxKlip.getChildren().add(dpStart);
        hboxKlip.getChildren().add(new Label("Til: "));
        hboxKlip.getChildren().add(dpSlut);

        HBox hboxKlip2 = new HBox();
        hboxKlip2.setSpacing(10);
        hboxKlip2.getChildren().add(new Label("Købte klippekort: "));
        hboxKlip2.getChildren().add(txfAntalKlipKøbt);
        hboxKlip2.getChildren().add(new Label("Bruge klippekort: "));
        hboxKlip2.getChildren().add(txfAntalKlipBrugt);
        hboxKlip2.getChildren().add(btnVisSpecifik);




        VBox vBoxfuldKlip = new VBox();
        vBoxfuldKlip.setPadding(new Insets(5));
        vBoxfuldKlip.setStyle(cssLayout);
        vBoxfuldKlip.setSpacing(20);
        vBoxfuldKlip.getChildren().add(hboxKlip);
        vBoxfuldKlip.getChildren().add(hboxKlip2);
        this.add(vBoxfuldKlip, 0, 1, 2, 2);


    }
    private void updateSalg(){
        LocalDate dato = dpDag.getValue();
        Dagsproduktion dagsproduktion;

        double udlejningOmsæt = 0;

        if(dpDag.getValue().equals(LocalDate.now())){
            dagsproduktion = Dagsproduktion.getDagsproduktion();
        }else{
            dagsproduktion = storage.getDagsproduktioner().get(dato);
        }

        for(Udlejning u : dagsproduktion.getDagensAfsluttedeUdlejninger()){
            udlejningOmsæt += u.samletPris();
        }
        txfOmsætningSalg.setText(dagsproduktion.getOmsætning() + "");
        txfOmsætningUdl.setText(udlejningOmsæt + "");
        txfAntalsalg.setText(dagsproduktion.getSalg().size()+"");
        txfAntalUdl.setText(dagsproduktion.getDagensAfsluttedeUdlejninger().size() + "");
    }

}

