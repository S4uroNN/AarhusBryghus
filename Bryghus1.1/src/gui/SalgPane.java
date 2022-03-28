package gui;

import application.model.Salg;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SalgPane extends GridPane {



    public SalgPane(){
        setHgap(20);
        setVgap(10);
        setGridLinesVisible(false);
        setMaxWidth(500);

        add(new Label("Morgen"), 0, 0);
        add(new Label("Middag"), 1, 0);
        add(new Label("Aften"), 2, 0);
        add(new Label("Nat"), 3, 0);
    }
}
