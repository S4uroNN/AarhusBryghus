package gui;

import application.model.Prisliste;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PrislistePane extends GridPane {
    private ListView<Prisliste> lvwPrislister = new ListView<>();
    private ListView<Vare> lvwVarer = new ListView<>();

    private Button btnAddPrisliste, btnDeletePrisliste, btnEditPrisliste;

    public PrislistePane(){
        this.setPadding(new Insets(20));
        setHgap(40);
        setVgap(10);
        setGridLinesVisible(true);
        setMaxWidth(1000);

        Label lblVareGruppe = new Label("Prislister");
        this.add(lblVareGruppe,0,0);
        this.add(lvwPrislister,0,1);
        this.add(lvwVarer,1,1);

        Label lblVarer = new Label("Varer");
        this.add(lblVarer, 1,0);



        btnAddPrisliste = new Button("Tilføj Prisliste");
        btnAddPrisliste.setPrefWidth(120);
        btnDeletePrisliste = new Button("Slet Prisliste");
        btnDeletePrisliste.setPrefWidth(120);
        btnEditPrisliste = new Button("Ret Prisliste");
        btnEditPrisliste.setPrefWidth(120);
        VBox vbox = new VBox();
        vbox.getChildren().add(btnAddPrisliste);
        vbox.getChildren().add(btnDeletePrisliste);
        vbox.getChildren().add(btnEditPrisliste);
        vbox.setSpacing(10);
        this.add(vbox,2,1);

    }
}
