package gui;

import application.controller.Controller;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.util.Optional;


public class VarePane extends GridPane {
    private ListView<Vare> lvwAlleVare = new ListView<>();
    private ListView<VareGruppe> lvwvareGruppe = new ListView<>();
    private ListView<Vare> lvwVareInGruppe = new ListView<>();

    private Button btnAddVareGruppe, btnDeleteVareGruppe, btnEditVareGruppe, btnÆndreVareGruppe;

    private Controller controller = Controller.getController();
    private Storage storage = Storage.getInstance();

    public VarePane() {

        controller.initStorage();
        updateLists();

        this.setPadding(new Insets(20));
        setHgap(20);
        setVgap(10);
        setGridLinesVisible(false);
        setMaxWidth(1200);

        Label lblVareGruppe = new Label("Vare Gruppe");
        this.add(lblVareGruppe, 0, 0);
        this.add(lvwvareGruppe, 0, 1, 1, 2);
        this.add(lvwVareInGruppe, 1, 1, 1, 2);

        Label lblVarerInGruppe = new Label("Vare I gruppe");
        this.add(lblVarerInGruppe, 1, 0);


        btnAddVareGruppe = new Button("Tilføj Vare Gruppe");
        btnAddVareGruppe.setPrefWidth(130);
        btnAddVareGruppe.setOnAction(event -> tilføjActionVareGruppe());
        btnDeleteVareGruppe = new Button("Slet Vare Gruppe");
        btnDeleteVareGruppe.setPrefWidth(130);
        btnDeleteVareGruppe.setOnAction(event -> sletActionVareGruppe());
        btnEditVareGruppe = new Button("Ret Vare Gruppe");
        btnEditVareGruppe.setPrefWidth(130);
        btnEditVareGruppe.setOnAction(event -> retActionVareGruppe());

        btnÆndreVareGruppe = new Button("Ændre vare gruppe");
        btnÆndreVareGruppe.setPrefWidth(130);
        btnÆndreVareGruppe.setOnAction(event -> ændreVareGruppe());
        VBox vbox2 = new VBox();
        vbox2.getChildren().add(btnÆndreVareGruppe);
        vbox2.getChildren().add(btnAddVareGruppe);
        vbox2.getChildren().add(btnDeleteVareGruppe);
        vbox2.getChildren().add(btnEditVareGruppe);
        vbox2.setSpacing(10);
        vbox2.setAlignment(Pos.BOTTOM_CENTER);
        this.add(vbox2, 2, 1);

        ChangeListener<VareGruppe> listener = (ov, oldArrangement, newArrangement) -> this.selectedVareGruppeChanged();
        lvwvareGruppe.getSelectionModel().selectedItemProperty().addListener(listener);
        lvwvareGruppe.getItems().setAll(storage.getVaregrupper());

    }

    private void selectedVareGruppeChanged() {
        this.updateLists();
    }

    private void updateLists() {
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();

        if (vareGruppe != null) {
            lvwVareInGruppe.getItems().setAll(vareGruppe.getVarer());
        }
        lvwAlleVare.getItems().setAll(storage.getVarer());
    }

    private void ændreVareGruppe() {
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();
        if (vareGruppe != null) {
            TilføjVareTilVareGruppeWindow tilføj = new TilføjVareTilVareGruppeWindow(vareGruppe);
            tilføj.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare gruppe!");
            alert.showAndWait();
        }

        lvwvareGruppe.getItems().setAll(storage.getVaregrupper());
    }


    private void tilføjActionVareGruppe() {
        TilføjVareGruppeWindow dia = new TilføjVareGruppeWindow("Tilføj VareGruppe");
        dia.showAndWait();

        lvwvareGruppe.getItems().setAll(storage.getVaregrupper());

    }

    private void sletActionVareGruppe() {
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();
        if (vareGruppe != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Slet Vare Gruppe");
            alert.setHeaderText("Er du sikker?");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                controller.deleteVareGruppe(vareGruppe);
                lvwvareGruppe.getItems().setAll(storage.getVaregrupper());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare gruppe!");
            alert.showAndWait();
        }
    }

    private void retActionVareGruppe() {
        VareGruppe vareGruppe = lvwvareGruppe.getSelectionModel().getSelectedItem();
        if (vareGruppe != null) {

            TilføjVareGruppeWindow dia = new TilføjVareGruppeWindow("Ret VareGruppe", vareGruppe);
            dia.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Du har ikke valgt en vare gruppe!");
            alert.showAndWait();
        }
        lvwvareGruppe.getItems().setAll(storage.getVaregrupper());
    }
}
