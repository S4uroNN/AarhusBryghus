package storage;

import application.model.Prisliste;
import application.model.Vare;
import application.model.Varegruppe;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Storage implements Serializable {
    private static Storage storage;

    private Set<Vare> varer = new HashSet<>();
    private Set<Prisliste> prislister = new HashSet<>();
    private Set<Varegruppe> varegrupper = new HashSet<>();

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

    public HashSet<Varegruppe> getVaregrupper() {
        return new HashSet<>(varegrupper);
    }

    public void addVaregruppe(Varegruppe varegruppe){
        varegrupper.add(varegruppe);
    }

    public void removeVaregruppe(Varegruppe varegruppe){
        varegrupper.remove(varegruppe);
    }

}