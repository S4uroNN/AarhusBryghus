package gui;


import application.controller.VareController;
import application.model.VareGruppe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TilføjVareGruppeWindow extends Stage {
    private VareGruppe vareGruppe;

    private VareController vareController;


    public TilføjVareGruppeWindow(String title, VareGruppe vareGruppe) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);



        this.vareGruppe = vareGruppe;

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    public TilføjVareGruppeWindow(String title) {
        this(title, null);
    }

    //-----------------------------------------------------------------------------------
    private TextField txfNavn, txfPant;
    private Label lblError;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        vareController = VareController.getController();

//------------------------------------------------------------------------------------
        Label lblNavn = new Label("Navn:");
        pane.add(lblNavn, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 1, 0);
        txfNavn.setPrefWidth(200);

        Label lblPant = new Label("Pant:");
        pane.add(lblPant, 0, 1);

        txfPant = new TextField();
        pane.add(txfPant, 1, 1);
        txfPant.setPrefWidth(200);

        Button btnCancelVareGruppe = new Button("Fortryd");
        btnCancelVareGruppe.setOnAction(event -> this.cancelActionVareGruppe());

        Button btnOKVareGruppe = new Button("Opret Varegruppe");
        btnOKVareGruppe.setOnAction(event -> this.okActionVareGruppe());

        lblError = new Label();
        pane.add(lblError, 0, 3, 2, 1);
        lblError.setStyle("-fx-text-fill: red");

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().add(btnOKVareGruppe);
        hBox.getChildren().add(btnCancelVareGruppe);
        pane.add(hBox, 1, 2);

        this.initControls();
    }

    public void stop() {
        vareController.saveStorage();
    }

    private void initControls() {
        if (vareGruppe != null) {
            txfNavn.setText(vareGruppe.getNavn());
            if(vareGruppe.getPant()>0){
                txfPant.setText(vareGruppe.getPant() + "");
            }
        } else {
            txfNavn.clear();
        }
    }

    private void cancelActionVareGruppe() {
        this.hide();
    }

    private void okActionVareGruppe() {
        int pantint;
        String pant = txfPant.getText().trim();
        String navn = txfNavn.getText().trim();
        if (navn.length() == 0) {
            lblError.setText("Navn er ikke angivet");
        } else if (pant.length()== 0) {
            lblError.setText("Pant er ikke angivet");
        } else {
            if (vareGruppe != null){
                pantint = Integer.parseInt(pant);
                vareController.updateVareGruppe(vareGruppe,navn,pantint);
            }
            else {
                pantint = Integer.parseInt(pant);
                vareController.createVareGruppe(navn, pantint);

            }
            this.hide();
        }

    }
}

