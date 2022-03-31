package gui;

import application.model.Prisliste;
import application.model.Salg;
import application.model.Vare;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import storage.Storage;

public class SalgPane extends GridPane {
    private ListView<Vare> lvwOrdre = new ListView<>();
    private Button btnTilføjVare, btnFjernVare;
    private ComboBox<Prisliste> prislisteComboBox;
    private RadioButton rbDankort, rbMobilepay, rbKontant, rbRegning, rbKlippekort;
    private RadioButton rbFastProcent, rbUserDefined, rbNoDiscount;
    private TextField txfRabat, txfSamletPris;
    private Storage storage = Storage.getInstance();
    public SalgPane(){
        setHgap(20);
        setVgap(10);
        setGridLinesVisible(false);
        setMaxWidth(500);


    }
}
