package application.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ordrelinje implements Serializable {
    private int antal;
    private Rabat rabat;
    private Vare vare;

    public Ordrelinje(int antal, Vare vare){
        this.antal = antal;
        this.vare = vare;
    }

    public int getAntal() {
        return antal;
    }

    public Vare getVare() {
        return vare;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public void setVare(Vare vare) {
        this.vare = vare;
    }

    public Rabat getRabat() {
        return rabat;
    }

    public void setRabat(Rabat rabat) {
        this.rabat = rabat;
    }

    @Override
    public String toString() {
        return vare + " , Antal: " + antal;
    }
}
