package gui;

import application.controller.Controller;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.Storage;

import java.util.List;
import java.util.Optional;

public class TilføjVareTilVareGruppeWindow extends Stage {
    private Vare vare;
    private VareGruppe vareGruppe;

    private Controller controller = Controller.getController();
    private Storage storage = Storage.getInstance();

    private ListView<Vare> lvwvareGruppeVare = new ListView<>();
    private ListView<Vare> lvwalleVarer = new ListView<>();

    private Button btnTilføjTilGruppe,btnFjernFraGruppe, btnTilføjVare,btnFjernVare, btnEditVare;

    public TilføjVareTilVareGruppeWindow(VareGruppe vareGruppe){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.vareGruppe = vareGruppe;

        if(vareGruppe != null){
            this.setTitle(vareGruppe.getNavn());
        }
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.initContent(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        pane.setPrefWidth(650);

        pane.add(new Label("Tilføjet"),0,0);
        pane.add(new Label("Vare"),1,0);

        pane.add(lvwvareGruppeVare,0,1);
        pane.add(lvwalleVarer,1,1);
        updateLists();

        int width = 125;
        btnEditVare = new Button("Ret vare");
        btnEditVare.setPrefWidth(width);
        btnEditVare.setOnAction(event -> retVareAction());
        btnTilføjVare = new Button("Tilføj ny vare");
        btnTilføjVare.setPrefWidth(width);
        btnTilføjVare.setOnAction(event -> tilføjActionVare());
        btnFjernVare = new Button("Slet vare");
        btnFjernVare.setPrefWidth(width);
        btnFjernVare.setOnAction(event -> sletVareAction());
        btnTilføjTilGruppe = new Button("Tilføj til gruppe");
        btnTilføjTilGruppe.setPrefWidth(width);
        btnTilføjTilGruppe.setOnAction(event -> vareTilVareGruppeAction() );
        btnFjernFraGruppe = new Button("Fjern fra gruppe");
        btnFjernFraGruppe.setPrefWidth(width);
        btnFjernFraGruppe.setOnAction(event -> fjernvareTilVareGruppeAction());

        VBox vbox = new VBox();
        vbox.getChildren().add(btnTilføjVare);
        vbox.getChildren().add(btnEditVare);
        vbox.getChildren().add(btnFjernVare);
        vbox.getChildren().add(btnTilføjTilGruppe);
        vbox.getChildren().add(btnFjernFraGruppe);
        vbox.setSpacing(10);
        pane.add(vbox,2,1);


    }

    private void updateLists(){
        if(vareGruppe != null){
            lvwvareGruppeVare.getItems().setAll(vareGruppe.getVarer());
            lvwalleVarer.getItems().setAll(storage.getVarer());
        }
        lvwalleVarer.getItems().setAll(storage.getVarer());
    }
    private void tilføjActionVare() {
        TilføjVareWindow tilføjVareWindow = new TilføjVareWindow();
        tilføjVareWindow.showAndWait();

        updateLists();

    }
    private void retVareAction(){
        Vare vare = lvwalleVarer.getSelectionModel().getSelectedItem();
        if(vare != null) {
            TilføjVareWindow ret = new TilføjVareWindow(vare);
            ret.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare!");
            alert.showAndWait();
        }
        updateLists();
    }
    private void sletVareAction(){
        Vare vare = lvwalleVarer.getSelectionModel().getSelectedItem();
        if(vare != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet Vare");
            alert.setHeaderText("Er du sikker?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                controller.deleteVare(vare);
                lvwalleVarer.getItems().setAll(storage.getVarer());
            }

        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare!");
            alert.showAndWait();
        }
        updateLists();
    }

    private void vareTilVareGruppeAction(){
        Vare vare = lvwalleVarer.getSelectionModel().getSelectedItem();
        if(vare != null){
            controller.addVareToVareGruppe(vare, vareGruppe);
            updateLists();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare!");
            alert.showAndWait();
        }
    }
    private void fjernvareTilVareGruppeAction(){
        Vare vare = lvwvareGruppeVare.getSelectionModel().getSelectedItem();
        if(vare != null){
            controller.removeVareFromGruppe(vare, vareGruppe);
            updateLists();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare!");
            alert.showAndWait();
        }
    }



}
