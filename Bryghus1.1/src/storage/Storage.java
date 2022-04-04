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
    private Map<LocalDate, Dagsproduktion> dagsproduktioner = new HashMap<>();
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

    public void addVare(Vare vare){
        varer.add(vare);
    }

    public void removeVare(Vare vare){
        varer.remove(vare);
    }


    public HashSet<Prisliste> getPrislister() {
        return new HashSet<>(prislister);
    }

    public void addPrisliste(Prisliste prisliste){
        prislister.add(prisliste);
    }

    public void removePrisliste(Prisliste prisliste){
        prislister.remove(prisliste);
    }


    public HashSet<VareGruppe> getVaregrupper() {
        return new HashSet<>(varegrupper);
    }

    public void addVareGruppe(VareGruppe varegruppe){
        varegrupper.add(varegruppe);
    }

    public void removeVareGruppe(VareGruppe varegruppe){
        varegrupper.remove(varegruppe);
    }


    public HashMap<LocalDate, Dagsproduktion> getDagsproduktioner(){
        return new HashMap<>(dagsproduktioner);
    }

    public void addDagsproduktion(LocalDate dato, Dagsproduktion dagsproduktion){
        dagsproduktioner.put(dato, dagsproduktion);
    }

    public void removeDagsproduktion(LocalDate dato){
        dagsproduktioner.remove(dato);
    }


    public HashSet<Udlejning> getAktiveUdlejninger(){
        return new HashSet<>(aktiveUdlejninger);
    }

    public void addUdlejning(Udlejning udlejning){
        aktiveUdlejninger.add(udlejning);
    }

    public void removeUdlejning (Udlejning udlejning){
        aktiveUdlejninger.remove(udlejning);
    }

}