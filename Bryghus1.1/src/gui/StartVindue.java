package gui;

import application.controller.VareController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartVindue extends Application {

    private VareController vareController;

    private void initContent(BorderPane pane) {
        vareController = VareController.getController();
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("Aarhus Bryghus - Aarhus Erhversakademi 1.1");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(850);
        stage.show();
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabOpret = new Tab("Salg");
        Tab tabOpretUdl = new Tab("Opret Udlejninger");
        Tab tabVisUdl = new Tab("Vis Udlejninger");
        Tab tabStatistik = new Tab("Salgsstatistik");
        Tab tabVisVare = new Tab("Vare");
        Tab tabPrisliste = new Tab("Prislister");

        VarePane varePane = new VarePane();
        tabVisVare.setContent(varePane);

        PrislistePane prislistePane = new PrislistePane();
        tabPrisliste.setContent(prislistePane);

        SalgPane salgPane = new SalgPane();
        tabOpret.setContent(salgPane);


        tabPane.getTabs().add(tabOpret);
        tabPane.getTabs().add(tabOpretUdl);
        tabPane.getTabs().add(tabVisUdl);
        tabPane.getTabs().add(tabStatistik);
        tabPane.getTabs().add(tabVisVare);
        tabPane.getTabs().add(tabPrisliste);


    }
}