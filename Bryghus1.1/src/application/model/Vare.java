package application.model;

import javafx.scene.control.skin.TooltipSkin;

public class Vare {
    private String navn;

    public Vare(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return navn;
    }

    public double getPris(Prisliste prisliste){
        return prisliste.getVarePris(this);
    }

    public int getKlipPris(Prisliste prisliste){
        return prisliste.getKlipPris(this);
    }

}
