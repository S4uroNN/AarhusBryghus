package gui;

import application.controller.SalgController;
import application.model.Vare;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class VisIkkeAfleveredeWindow extends Stage {
    private ListView<Vare> lvwikkeAfleveredeVare = new ListView<>();
    private Button btnOK;

    private SalgController salgController = SalgController.getSalgController();

    public VisIkkeAfleveredeWindow(){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Ikke afleverede vare");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        TextArea txa = new TextArea();
        pane.add(new Label("Vare"),0,0);
        pane.add(txa,0,1);

        salgController.loadStorage();

        txa.setText(String.valueOf(salgController.getUdlejedeVarer()));
        txa.setEditable(false);

        btnOK = new Button("OK");
        btnOK.setOnAction(event -> this.hide());
        pane.add(btnOK,0,2);

    }
}
