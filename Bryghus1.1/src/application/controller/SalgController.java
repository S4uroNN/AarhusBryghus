package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;

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

    public Salg createSalg(Dagsproduktion dagsproduktion, Prisliste prisliste) {
        Salg salg = dagsproduktion.createSalg(prisliste);
        return salg;
    }

    public Udlejning createUdlejning(LocalDate startDato, LocalDate slutDato, String kontaktPerson, String telefonnr, String email, Prisliste prisliste){
        Udlejning udlejning = new Udlejning(startDato, slutDato, kontaktPerson, telefonnr, email, prisliste);
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    public Udlejning afslutUdlejning(Udlejning udlejning, Dagsproduktion dagsproduktion){
        dagsproduktion.addafsluttetUdlejning(udlejning);
        udlejning.setAsAfsluttet();
        storage.removeUdlejning(udlejning);
        return udlejning;
    }

    public Ordrelinje createOrdrelinjeSalg(Salg salg, int antal, Vare vare) {
        Ordrelinje ordrelinje = salg.createOrdreLinje(antal, vare);
        return ordrelinje;
    }

    public Ordrelinje createOrdrelinjeUdlejning(Udlejning udlejning, int antal, Vare vare) {
        Ordrelinje ordrelinje = udlejning.createOrdreLinje(antal, vare);
        return ordrelinje;
    }

    public void updateOmsætning(Dagsproduktion dagsproduktion){
        dagsproduktion.updateOmsætning();
    }


}
