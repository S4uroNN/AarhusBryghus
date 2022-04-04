package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public static Dagsproduktion getDagsproduktion() {
        Dagsproduktion dagsproduktion = Dagsproduktion.getDagsproduktion();
        return dagsproduktion;
    }

    public Salg createSalg(Dagsproduktion dagsproduktion, Prisliste prisliste) {
        Salg salg = dagsproduktion.createSalg(prisliste);
        return salg;
    }

    public Udlejning createUdlejning(LocalDate startDato, LocalDate slutDato, String kontaktPerson, String telefonnr, String email, Prisliste prisliste) {
        Udlejning udlejning = new Udlejning(startDato, slutDato, kontaktPerson, telefonnr, email, prisliste);
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    public Udlejning afslutUdlejning(Udlejning udlejning, Dagsproduktion dagsproduktion, Betalingsform betalingsform) {
        udlejning.setBetalingsform(betalingsform);
        dagsproduktion.addafsluttetUdlejning(udlejning);
        udlejning.setAsAfsluttet();
        storage.removeUdlejning(udlejning);
        return udlejning;
    }

    public Ordrelinje createOrdrelinjeSalg(Salg salg, int antal, Vare vare) {
        Ordrelinje ordrelinje = salg.createOrdreLinje(antal, vare);
        return ordrelinje;
    }

    public void removeOrdrelinjeSalg(Salg salg, Ordrelinje ordrelinje){
        salg.removeOrdrelinje(ordrelinje);
    }

    public Ordrelinje createOrdrelinjeUdlejning(Udlejning udlejning, int antal, Vare vare) {
        Ordrelinje ordrelinje = udlejning.createOrdreLinje(antal, vare);
        return ordrelinje;
    }

    public void removeOrdrelinjeUdlejning(Udlejning udlejning, Ordrelinje ordrelinje){
        udlejning.removeOrdreLinje(ordrelinje);
    }


    public void updateOmsætning(Dagsproduktion dagsproduktion) {
        dagsproduktion.updateOmsætning();
    }

    public Map<Vare, Integer> getUdlejedeVarer() {
        Map<Vare, Integer> udlejedeVarer = new HashMap<>();
        for (Udlejning udlejning : storage.getAktiveUdlejninger()) {
            for (Ordrelinje ordrelinje : udlejning.getOrdrelinjer()) {
                Vare vare = ordrelinje.getVare();
                int antal = ordrelinje.getAntal();
                if (!udlejedeVarer.containsKey(vare)) {
                    udlejedeVarer.put(vare, antal);
                } else {
                    udlejedeVarer.replace(vare, udlejedeVarer.get(vare) + antal);
                }
            }
        }
        return udlejedeVarer;
    }


    public int getSolgteKlip() {
        int solgteKlip = 0;
        for (Dagsproduktion dagsproduktion : controller.storage.getDagsproduktioner()){
            for (Salg salg : dagsproduktion.getSalg()){
                for (Ordrelinje ordrelinje : salg.getOrdrelinjer()){
                    if (ordrelinje.getVare().getNavn().equalsIgnoreCase("Klippekort")){
                        solgteKlip += ordrelinje.getAntal() * 10;
                    }
                }
            }
        }
        return solgteKlip;
    }

}
