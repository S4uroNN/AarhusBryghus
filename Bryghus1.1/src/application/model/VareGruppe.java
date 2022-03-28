package application.model;

import java.util.HashSet;
import java.util.Set;

public class VareGruppe {
    private String navn;

    private final Set varer = new HashSet();

    public VareGruppe(String navn){
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void addVare(Vare vare){
        varer.add(vare);
    }

    public void removeVare(Vare vare){
        varer.remove(vare);
    }
    public HashSet<Vare> getVarer(){
        return new HashSet<>(varer);
    }

}
