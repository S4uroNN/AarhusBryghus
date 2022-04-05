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

    public Salg createSalg(Prisliste prisliste) {
        Salg salg = new Salg(prisliste);
        storage.addSalg(salg);
        return salg;
    }

    public Udlejning createUdlejning(LocalDate startDato, LocalDate slutDato, String kontaktPerson, String telefonnr, String email, Prisliste prisliste) {
        Udlejning udlejning = new Udlejning(startDato, slutDato, kontaktPerson, telefonnr, email, prisliste);
        storage.addAktivUdlejning(udlejning);
        return udlejning;
    }

    public Udlejning afslutUdlejning(Udlejning udlejning, Betalingsform betalingsform) {
        udlejning.setBetalingsform(betalingsform);
        storage.removeAktivUdlejning(udlejning);
        storage.addAfsluttetUdlejning(udlejning);
        return udlejning;
    }

    public Ordrelinje createOrdrelinjeSalg(Salg salg, int antal, Vare vare) {
        Ordrelinje ordrelinje = salg.createOrdreLinje(antal, vare);
        return ordrelinje;
    }

    public void removeOrdrelinjeSalg(Salg salg, Ordrelinje ordrelinje) {
        salg.removeOrdrelinje(ordrelinje);
    }

    public Ordrelinje createOrdrelinjeUdlejning(Udlejning udlejning, int antal, Vare vare) {
        Ordrelinje ordrelinje = udlejning.createOrdreLinje(antal, vare);
        return ordrelinje;
    }

    public void removeOrdrelinjeUdlejning(Udlejning udlejning, Ordrelinje ordrelinje) {
        udlejning.removeOrdreLinje(ordrelinje);
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


    public int getSolgteKlip(LocalDate startdato, LocalDate slutdato) {
        int solgteKlip = 0;
        LocalDate dato = startdato;
        while (dato.compareTo(slutdato) < 1) {
            for (Salg salg : storage.getSalg().get(dato)) {
                for (Ordrelinje ordrelinje : salg.getOrdrelinjer()) {
                    if (ordrelinje.getVare().getNavn().equalsIgnoreCase("Klippekort")) {
                        solgteKlip += ordrelinje.getAntal() * 10;
                    }
                }
            }
            dato = dato.plusDays(1);
        }
        return solgteKlip;
    }

    public int getBrugteKlip(LocalDate startdate, LocalDate slutdato) {
        int brugteKlip = 0;
        LocalDate dato = startdate;
        while (dato.compareTo(slutdato) < 1) {
            for (Salg salg : storage.getSalg().get(dato)) {
                if (salg.getBetalingsform().equals(Betalingsform.KLIPPEKORT))
                    brugteKlip += salg.samletPrisKlip();
            }
            dato = dato.plusDays(1);
        }
        return brugteKlip;
    }

    public double getOmsætning(LocalDate dato) {
        double omsætning = 0;
        if (storage.getSalg().get(dato) != null) {
            for (Salg salg : storage.getSalg().get(dato)) {
                omsætning += salg.samletPris();
            }
        }
        if (storage.getAfsluttedeUdlejninger().get(dato) != null) {
            for (Udlejning udlejning : storage.getAfsluttedeUdlejninger().get(dato)) {
                omsætning += udlejning.samletPris();
            }
        }
        return omsætning;
    }

    public double getUdlejningsOmsætning(LocalDate dato) {
        double omsætning = 0;
        for (Udlejning udlejning : storage.getAfsluttedeUdlejninger().get(dato)) {
            omsætning += udlejning.samletPris();
        }
        return omsætning;
    }

}
