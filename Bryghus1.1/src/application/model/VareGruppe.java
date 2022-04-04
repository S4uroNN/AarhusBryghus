package application.model;

import java.util.HashSet;
import java.util.Set;

public class VareGruppe {
    private String navn;
    private int pant;

    private final Set<Vare> varer = new HashSet<>();

    public VareGruppe(String navn, int pant) {
        this.navn = navn;
        this.pant = pant;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void addVare(Vare vare) {
        varer.add(vare);
        vare.setVareGruppe(this);
    }

    public void removeVare(Vare vare) {
        varer.remove(vare);
        vare.setVareGruppe(null);
    }

    public void setPant(int pant) {
        this.pant = pant;
    }

    public int getPant() {
        return pant;
    }

    public HashSet<Vare> getVarer() {
        return new HashSet<>(varer);
    }

    @Override
    public String toString() {
        if (pant != 0) {
            return navn + "; Pant: " + pant;
        } else
            return navn;

    }
}
