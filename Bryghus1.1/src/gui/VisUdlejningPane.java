package gui;

import application.model.Dagsproduktion;
import application.model.Udlejning;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import storage.Storage;

import java.time.LocalDate;


public class VisUdlejningPane extends GridPane {
    private ListView<Udlejning> lvwDagsProduktion = new ListView();


    private Storage storage = Storage.getInstance();
    private Dagsproduktion dagsproduktion;
    public VisUdlejningPane() {

        this.setPadding(new Insets(20));
        setHgap(20);
        setHgap(10);
        setGridLinesVisible(false);

        for(Dagsproduktion d : storage.getDagsproduktioner()){
            if(d.getDato() == LocalDate.now()){
                dagsproduktion = d;
            }
        }

        this.add(lvwDagsProduktion,0,0);
        if(dagsproduktion != null){
            lvwDagsProduktion.getItems().setAll(dagsproduktion.getDagensAfsluttedeUdlejninger());
        }


    }
}
