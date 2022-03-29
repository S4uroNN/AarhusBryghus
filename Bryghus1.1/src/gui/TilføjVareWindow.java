package gui;

import application.controller.Controller;
import application.model.Vare;
import application.model.VareGruppe;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.image.renderable.ContextualRenderedImageFactory;

public class TilføjVareWindow extends Stage {
    private final VareGruppe vareGruppe;
    private Controller controller = Controller.getController();

    private TextField txfNavn;
    private Button btnOpretVare, btnCancel;

    private Label lblError;


    public TilføjVareWindow(VareGruppe vareGruppe){
        this.vareGruppe = vareGruppe;

        this.setTitle("Tilføj Vare");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.initContent(pane);

    }
    private void initContent(GridPane pane){
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        pane.setPrefWidth(250);

        pane.add(new Label("VareGruppe: " + vareGruppe),0,0,2,1);
        pane.add(new Label("Navn"),0,1);

        txfNavn = new TextField();
        pane.add(txfNavn,1,1);

        btnOpretVare = new Button("Opret Vare");
        btnCancel = new Button("Fortryd");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(btnOpretVare);
        hBox.getChildren().add(btnCancel);
        pane.add(hBox,1,2,2,1);

        btnOpretVare.setOnAction(event -> opretVare());
        btnCancel.setOnAction(event -> this.hide());

        lblError = new Label();
        pane.add(lblError, 0, 3, 2, 1);
        lblError.setStyle("-fx-text-fill: red");

    }
    private void opretVare(){
        String navn = txfNavn.getText().trim();
        if(navn.length() == 0){
            lblError.setText("Navn er ikke angivet");
        }else{
            Vare vare = controller.createVare(navn);
            controller.addVareToVareGruppe(vare, vareGruppe);
            this.hide();
        }
    }

}
