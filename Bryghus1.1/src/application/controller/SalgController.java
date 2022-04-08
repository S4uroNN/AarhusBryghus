package application.controller;

import application.model.*;
import storage.Storage;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public static SalgController getTestController() {
        return new SalgController();
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
            if (storage.getSalg().get(dato) != null) {
                for (Salg salg : storage.getSalg().get(dato)) {
                    for (Ordrelinje ordrelinje : salg.getOrdrelinjer()) {
                        if (ordrelinje.getVare().getNavn().equalsIgnoreCase("Klippekort")) {
                            solgteKlip += ordrelinje.getAntal() * 4;
                        }
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
            if ((storage.getSalg().get(dato) != null)) {
                for (Salg salg : storage.getSalg().get(dato)) {
                    if (salg.getBetalingsform().equals(Betalingsform.KLIPPEKORT))
                        brugteKlip += salg.samletPrisKlip();
                }
            }
            dato = dato.plusDays(1);
        }
        return brugteKlip;
    }

    public double getOmsætning(LocalDate dato) {
        double omsætning = 0;
        if (storage.getSalg().get(dato) != null) {
            for (Salg salg : storage.getSalg().get(dato)) {
                if(salg.getBetalingsform() != Betalingsform.KLIPPEKORT){
                    omsætning += salg.samletPris();
                }
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
    public double getSamletPrisKlip(Salg salg){
        return salg.samletPrisKlip();
    }
    public double getSamletPris(Salg salg){
        double pris = salg.samletPris();
        return pris;
    }
    public Set<Ordrelinje> getSalgOrdreLinjer(Salg salg){
        return salg.getOrdrelinjer();
    }

    public double getSamletPrisUDl(Udlejning udlejning){
        return udlejning.samletPris();
    }
    public Set<Ordrelinje> getUDlOrdrelinjer(Udlejning udlejning){
        return udlejning.getOrdrelinjer();
    }

    public void setFastRabatSalg(Salg salg, double beloeb) {
        salg.setRabat(new KontantRabat(beloeb));
    }

    public void setProcentRabatSalg(Salg salg, double beloeb) {
        salg.setRabat(new ProcentRabat(beloeb));
    }

    public void setFastRabatUdlejning(Udlejning udlejning, double beloeb) {
        udlejning.setRabat(new KontantRabat(beloeb));
    }

    public void setProcentRabatUdlejning(Udlejning udlejning, double beloeb) {
        udlejning.setRabat(new ProcentRabat(beloeb));
    }

    public void setProcentRabatOrdrelinje(Ordrelinje ordrelinje, double beloeb) {
        ordrelinje.setRabat(new ProcentRabat(beloeb));
    }

    public void setFastRabatOrdrelinje(Ordrelinje ordrelinje, double beloeb) {
        ordrelinje.setRabat(new KontantRabat(beloeb));
    }

    public void loadStorage() {
        try (FileInputStream fileIn = new FileInputStream("storage.ser")) {
            try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
                storage = (Storage) in.readObject();

                System.out.println("Storage loaded from file storage.ser.");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error loading storage object.");
                throw new RuntimeException(ex);
            }
        } catch (IOException ex) {
            System.out.println("Error loading storage object.");
            throw new RuntimeException(ex);
        }

    }

    public void saveStorage() {
        try (FileOutputStream fileOut = new FileOutputStream("storage.ser")) {
            try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(storage);
                System.out.println("Storage saved in file storage.ser.");
            }
        } catch (IOException ex) {
            System.out.println("Error saving storage object.");
            throw new RuntimeException(ex);
        }
    }

}
