package gui;

import application.controller.SalgController;
import application.controller.VareController;
import application.model.Dagsproduktion;
import application.model.Udlejning;
import application.model.Vare;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.SubtitleTrack;
import storage.Storage;

import java.security.spec.RSAKeyGenParameterSpec;
import java.time.LocalDate;


public class VisUdlejningPane extends GridPane {
    private ListView<Udlejning> lvwDagsProduktion = new ListView();


    private Storage storage = Storage.getInstance();
    private Dagsproduktion dagsproduktion = SalgController.getDagsproduktion();
    public VisUdlejningPane() {

        this.setPadding(new Insets(20));
        setHgap(20);
        setHgap(10);
        setGridLinesVisible(false);

        this.add(lvwDagsProduktion,0,0);
        System.out.println(dagsproduktion.getDagensAfsluttedeUdlejninger());
        System.out.println(dagsproduktion);
        lvwDagsProduktion.getItems().setAll(dagsproduktion.getDagensAfsluttedeUdlejninger());


        Button btn = new Button("Opdater");
        this.add(btn,3,3);
        btn.setOnAction(event ->   lvwDagsProduktion.getItems().setAll(storage.getAktiveUdlejninger()));


    }
}
