package storage;

import application.model.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Storage implements Serializable {
    private static Storage storage;

    private Set<Vare> varer = new HashSet<>();
    private Set<Prisliste> prislister = new HashSet<>();
    private Set<VareGruppe> varegrupper = new HashSet<>();
    private Map<LocalDate, Set<Salg>> alleSalg = new HashMap<>();
    private Map<LocalDate, Set<Udlejning>> afslutedeUdlejninger = new HashMap<>();
    private Set<Udlejning> aktiveUdlejninger = new HashSet<>();

    private Storage() {
    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
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
        return new HashMap<>(afslutedeUdlejninger);
    }

    public void addAfsluttetUdlejning(Udlejning udlejning) {
        LocalDate dato = LocalDate.now();
        if (afslutedeUdlejninger.get(dato) == null) {
            afslutedeUdlejninger.put(dato, new HashSet<Udlejning>());
        }
        afslutedeUdlejninger.get(dato).add(udlejning);
    }

    public void removeAfsluttetUdlejning(LocalDate dato, Udlejning udlejning) {
        afslutedeUdlejninger.get(dato).remove(udlejning);
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