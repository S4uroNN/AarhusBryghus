package application.model;

import java.util.ArrayList;
import java.util.List;

public class Ordrelinje {
    private int linjenummer;
    private int antal;

    private Vare vare;

    public Ordrelinje(int linjenummer, int antal, Vare vare){
        this.linjenummer = linjenummer;
        this.antal = antal;
        this.vare = vare;
    }

    public int getAntal() {
        return antal;
    }

    public int getLinjenummer() {
        return linjenummer;
    }

    public Vare getVare() {
        return vare;
    }

    public void setLinjenummer(int linjenummer) {
        this.linjenummer = linjenummer;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public void setVare(Vare vare) {
        this.vare = vare;
    }
}
