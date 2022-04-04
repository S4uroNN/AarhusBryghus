package gui;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VisStatistikPane extends GridPane {
    private DatePicker dpStart, dpSlut, dpDag;

    private TextField txfOmsætningSalg, txfOmsætningUdl, txfAntalsalg, txfAntalUdl, txfAntalKlipKøbt, txfAntalKlipBrugt;

    private Button btnVisIkkeAfl, btnVisSpecifik, btnOpdaterSalg, getBtnOpdaterKlip;
    private int width = 110;

    public VisStatistikPane() {
        this.setPadding(new Insets(20));
        setHgap(20);
        setHgap(10);
        setGridLinesVisible(false);

        //SALG-----------------------------
        dpDag = new DatePicker();

        txfAntalsalg = new TextField();
        txfAntalsalg.setPrefWidth(width);
        txfOmsætningSalg = new TextField();
        txfOmsætningSalg.setPrefWidth(width);

        HBox salgogdato = new HBox();
        salgogdato.setSpacing(66); //Hej Margrethe :)
        salgogdato.getChildren().add(new Label("Salg"));
        salgogdato.getChildren().add(new Label("Dag"));
        salgogdato.getChildren().add(dpDag);

        HBox antalOgOmsætning = new HBox();
        antalOgOmsætning.setSpacing(10);
        antalOgOmsætning.getChildren().add(new Label("Antal: "));
        antalOgOmsætning.getChildren().add(txfAntalsalg);
        antalOgOmsætning.getChildren().add(new Label("Omsætning: "));
        antalOgOmsætning.getChildren().add(txfOmsætningSalg);

        VBox salgVbox = new VBox();
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

        HBox hboxUdl = new HBox();
        hboxUdl.setSpacing(10);
        hboxUdl.getChildren().add(new Label("Antal: "));
        hboxUdl.getChildren().add(txfAntalUdl);
        hboxUdl.getChildren().add(new Label("Omsætning: "));
        hboxUdl.getChildren().add(txfOmsætningUdl);

        VBox vboxUdl = new VBox();
        vboxUdl.setSpacing(18); // Hej Margrethe :)
        vboxUdl.getChildren().add(new Label("Udlejninger"));
        vboxUdl.getChildren().add(hboxUdl);
        vboxUdl.getChildren().add(btnVisIkkeAfl);
        vboxUdl.getChildren().add(btnOpdaterSalg);
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
        vBoxfuldKlip.setSpacing(20);
        vBoxfuldKlip.getChildren().add(hboxKlip);
        vBoxfuldKlip.getChildren().add(hboxKlip2);
        this.add(vBoxfuldKlip, 0, 1, 2, 2);


    }
    private void updateSalg(){

    }
}

