package application.model;

import java.util.ArrayList;
import java.util.List;

public class Ordrelinje {
    private int antal;

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
}
