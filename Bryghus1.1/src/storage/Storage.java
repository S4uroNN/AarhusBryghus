package storage;

import application.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Storage implements Serializable {
    private static Storage storage;

    private Set<Vare> varer;
    private Set<Prisliste> prislister;
    private Set<VareGruppe> varegrupper;
    private Map<LocalDate, Set<Salg>> alleSalg;
    private Map<LocalDate, Set<Udlejning>> afsluttedeUdlejninger;
    private Set<Udlejning> aktiveUdlejninger;

    public static Storage getInstance() {
        if (storage == null) {
            try (FileInputStream fileIn = new FileInputStream("storage.ser")) {
                try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
                    storage = (Storage) in.readObject();
                    System.out.println("Storage loaded from file storage.ser.");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Error loading storage object.");
                    throw new RuntimeException(ex);
                }
            } catch (IOException ex) {
                storage = new Storage();
            }
        }
        return storage;
    }

    private Storage(){
        varer = new HashSet<>();
        prislister = new HashSet<>();
        varegrupper = new HashSet<>();
        alleSalg = new HashMap<>();
        afsluttedeUdlejninger = new HashMap<>();
        aktiveUdlejninger = new HashSet<>();
    }

    public HashSet<Vare> getVarer() {
        return new HashSet<>(varer);
    }

    public void addVare(Vare vare) {
        varer.add(vare);
    }

    public void removeVare(Vare vare) {
        varer.remove(vare);
    }


    public HashSet<Prisliste> getPrislister() {
        return new HashSet<>(prislister);
    }

    public void addPrisliste(Prisliste prisliste) {
        prislister.add(prisliste);
    }

    public void removePrisliste(Prisliste prisliste) {
        prislister.remove(prisliste);
    }


    public HashSet<VareGruppe> getVaregrupper() {
        return new HashSet<>(varegrupper);
    }

    public void addVareGruppe(VareGruppe varegruppe) {
        varegrupper.add(varegruppe);
    }

    public void removeVareGruppe(VareGruppe varegruppe) {
        varegrupper.remove(varegruppe);
    }


    public HashMap<LocalDate, Set<Salg>> getSalg() {
        return new HashMap<>(alleSalg);
    }

    public void addSalg(Salg salg) {
        LocalDate dato = LocalDate.now();
        if (alleSalg.get(dato) == null) {
            alleSalg.put(dato, new HashSet<Salg>());
        }
        alleSalg.get(dato).add(salg);
    }


    public HashMap<LocalDate, Set<Udlejning>> getAfsluttedeUdlejninger() {
        return new HashMap<>(afsluttedeUdlejninger);
    }

    public void addAfsluttetUdlejning(Udlejning udlejning) {
        LocalDate dato = LocalDate.now();
        if (afsluttedeUdlejninger.get(dato) == null) {
            afsluttedeUdlejninger.put(dato, new HashSet<>());
        }
        afsluttedeUdlejninger.get(dato).add(udlejning);
    }

    public void removeAfsluttetUdlejning(LocalDate dato, Udlejning udlejning) {
        afsluttedeUdlejninger.get(dato).remove(udlejning);
    }


    public HashSet<Udlejning> getAktiveUdlejninger() {
        return new HashSet<>(aktiveUdlejninger);
    }

    public void addAktivUdlejning(Udlejning udlejning) {
        aktiveUdlejninger.add(udlejning);
    }

    public void removeAktivUdlejning(Udlejning udlejning) {
        aktiveUdlejninger.remove(udlejning);
    }

}