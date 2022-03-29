package gui;

import application.model.Prisliste;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PrislistePane extends GridPane {
    private ListView<Prisliste> lvwPrislister = new ListView<>();
    private ListView<Vare> lvwVarer = new ListView<>();

    private Button btnAddPrisliste, btnDeletePrisliste, btnEditPrisliste, btnTilføjVare, btntilføjVareGruppe, btnFjernVare;

    public PrislistePane(){
        this.setPadding(new Insets(20));
        setHgap(40);
        setVgap(10);
        setGridLinesVisible(false);
        setMaxWidth(1000);

        Label lblVareGruppe = new Label("Prislister");
        this.add(lblVareGruppe,0,0);


        Label lblVarer = new Label("Varer");
        this.add(lblVarer, 1,0);

        this.add(lvwPrislister,0,1,1,2);
        this.add(lvwVarer,1,1,1,2);


        btnAddPrisliste = new Button("Tilføj Prisliste");
        btnAddPrisliste.setPrefWidth(120);
        btnDeletePrisliste = new Button("Slet Prisliste");
        btnDeletePrisliste.setPrefWidth(120);
        btnEditPrisliste = new Button("Ret Prisliste");
        btnEditPrisliste.setPrefWidth(120);
        VBox upperVbox = new VBox();
        upperVbox.getChildren().add(btnAddPrisliste);
        upperVbox.getChildren().add(btnDeletePrisliste);
        upperVbox.getChildren().add(btnEditPrisliste);
        upperVbox.setSpacing(10);
        this.add(upperVbox,2,1);

        btnTilføjVare = new Button("Tilføj Vare");
        btnTilføjVare.setPrefWidth(120);
        btntilføjVareGruppe = new Button("Slet Vare");
        btntilføjVareGruppe.setPrefWidth(120);
        btnFjernVare = new Button("Fjern Vare");
        btnFjernVare.setPrefWidth(120);
        VBox lowerVbox = new VBox();
        lowerVbox.getChildren().add(btntilføjVareGruppe);
        lowerVbox.getChildren().add(btnTilføjVare);
        lowerVbox.getChildren().add(btnFjernVare);
        lowerVbox.setSpacing(10);
        this.add(lowerVbox,2,2);
        lowerVbox.setAlignment(Pos.BOTTOM_RIGHT);

        btnAddPrisliste.setOnAction(event -> tilføjAction());

    }
    private void tilføjAction() {
        TilføjPrisListeWindow tilføj = new TilføjPrisListeWindow("Tilføj Prisliste");
        tilføj.showAndWait();
    }

}
