package application.controller;

import application.model.*;
import storage.Storage;

public class SalgController {
    private Storage storage;
    private static SalgController controller;

    private SalgController() {
        storage = Storage.getInstance();
    }

    public static SalgController getSalgController() {
        if (controller == null) {
            controller = new SalgController();
        }
        return controller;
    }

    public Dagsproduktion createDagsproduktion() {
        Dagsproduktion dagsproduktion = new Dagsproduktion();
        storage.addDagsproduktion(dagsproduktion);
        return dagsproduktion;
    }

    public Salg createSalg(Dagsproduktion dagsproduktion) {
        Salg salg = dagsproduktion.createSalg();
        return salg;
    }

    public Ordrelinje createOrdrelinje(Salg salg, int antal, Vare vare) {
        Ordrelinje ordrelinje = salg.createOrdreLinje(antal, vare);
        return ordrelinje;
    }



}
