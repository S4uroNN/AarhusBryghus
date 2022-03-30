package storage;

import application.model.Dagsproduktion;
import application.model.Prisliste;
import application.model.Vare;
import application.model.VareGruppe;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Storage implements Serializable {
    private static Storage storage;

    private Set<Vare> varer = new HashSet<>();
    private Set<Prisliste> prislister = new HashSet<>();
    private Set<VareGruppe> varegrupper = new HashSet<>();
    private Set<Dagsproduktion> dagsproduktioner = new HashSet<>();

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

    public HashSet<Dagsproduktion> getDagsproduktioner(){
        return new HashSet<>(dagsproduktioner);
    }

    public void addDagsproduktion(Dagsproduktion dagsproduktion){
        dagsproduktioner.add(dagsproduktion);
    }

    public void removeDagsproduktion(Dagsproduktion dagsproduktion){
        dagsproduktioner.remove(dagsproduktion);
    }

}